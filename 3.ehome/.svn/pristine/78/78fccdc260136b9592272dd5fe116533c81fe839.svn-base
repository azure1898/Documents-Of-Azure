// ------------------ GENERAL FUNCTIONS ---------------------------//
var feedbackjs = function () {
    return {
        FeedbackNote: {
            extraSpaces: "NOTE: You are leaving extra spaces at the beginning/end of your transcription, please be sure to remove extra blank spaces at the beginning/end."
        }
    };
}();

function addCorrectStyle(feedbackDIV, isTXcorrect) {
    // correct or incorrect?
    if (isTXcorrect == true) {
        $(feedbackDIV).css("border", "3px solid green");
        insertFeedbackImage(feedbackDIV, "correct");
    }
    else {
        $(feedbackDIV).css("border", "3px solid red");
        insertFeedbackImage(feedbackDIV, "incorrect");
    }
}

// function to insert a green flag/red exclamation mark into the feedback box
function insertFeedbackImage(feedbackDIV, correctOrIncorrect) {
    if (correctOrIncorrect == "correct") {
        var newIMG = $("<img />");
        newIMG.attr("src", "/Content/page/image/training/feedback/flag.png");

        $(feedbackDIV).prepend(newIMG);
    }
    else {
        var newIMG = $("<i></i>");
        newIMG.addClass("fa fa-exclamation");
        newIMG.css("color", "red");

        $(feedbackDIV).prepend(newIMG);
    }
}

// function to highlight mismatching words in correct transcription
function highlightFeedbackWords(highlightCorrectWords, correct_transcription, feedbackDIV) {

    // convert transcription to word array
    var correctWords = correct_transcription.trim().split(" ");
    var n = correctWords.length;

    // reset contents of feedback box (if applicable)
    $(feedbackDIV).html("");
    // add each word, highlighted or not
    for (var i = 0; i < n; i++) {
        var newWord = document.createTextNode(correctWords[i] + " ");
        // mismatched word needs highlighting via <span> element
        if (highlightCorrectWords[i] == true) {
            var newSpan = document.createElement("span");
            newSpan.className = "wrong";
            newSpan.appendChild(newWord);
            $(feedbackDIV).append(newSpan);
        }
            // matching word can be added as-is
        else {
            $(feedbackDIV).append(newWord);
        }
    }
}

// ------------------ CHECK TRANSCRIPTION -------------------------//

// check whether trainee transcription is correct 
// when the user clicks on the "Check" button for a given audio file
function checkTranscriptionFeedback(element) {
    // create custom attribute for this button to store the result in
    // NOTE:	Make sure to not create the attribute twice!!

    var correct_transcription = $(element).find(".answer span").text();

    var trainee_transcription = $(element).find("textarea").val();

    // align correct and trainee transcriptions, and highlight mismatching words
    var alignmentResult = alignStrings(trainee_transcription, correct_transcription);
    var highlightWords = alignmentResult[0];
    var isTXcorrect = alignmentResult[1];

    // display feedback (correct vs. trainee) in feedback box
    var feedbackDiv = $(element).find(".feedback");
    feedbackDiv.closest(".form-group").removeClass("hidden");

    if (isTXcorrect == true) {
        feedbackDiv.html(replaceAngledBrackets(correct_transcription));
    }
    else {
        // highlight mismatching words in feedback
        var highlightCorrectWords = highlightWords[0];
        highlightFeedbackWords(highlightCorrectWords, correct_transcription, feedbackDiv);
    }

    //if trainee_transcription is not empty
    if (trainee_transcription.length > 0) {
        var trainee_transcription_trim = trainee_transcription.trim();

        var spaceindex = trainee_transcription.indexOf(trainee_transcription_trim);

        //if trainee transcriptions left is not space
        if (spaceindex != 0) {
            isTXcorrect = false;
            feedbackDiv.html(feedbackDiv.html() + "<br/><span class='wrong'style='font-style: italic;'>" + feedbackjs.FeedbackNote.extraSpaces + "</span>");
        }
        else {
            if (trainee_transcription_trim.length != trainee_transcription.length) {
                isTXcorrect = false;
                feedbackDiv.html(feedbackDiv.html() + "<br/><span class='wrong'style='font-style: italic;'>" + feedbackjs.FeedbackNote.extraSpaces + "</span>");
            }
        }
    }

    // add styling (red/green) to feedback box
    addCorrectStyle(feedbackDiv, isTXcorrect);

    return isTXcorrect;
}

// function to allow display of transcription tags in HTML elements
// (replacing angled brackets with HTML symbols)
function replaceAngledBrackets(inputText) {
    return inputText.replace(/</g, "&lt;").replace(/>/g, "&gt;");
}

// STRING ALIGNMENT FUNCTION
// NOTE:This function uses a dynamic programming algorithm for sequence alignment
// (Needleman/Wunsch) to compute the optimal alignment on a word basis.
function alignStrings(trainee_transcription, correct_transcription) {

    // get arrays of words for each transcription
    var traineeWords = trainee_transcription.trim().split(" ");
    var correctWords = correct_transcription.trim().split(" ");
    var m = traineeWords.length;
    var n = correctWords.length;

    // (mis)match and gap penalties
    // NOTE:	The match and mismatch penalties are defined in the checkMatch function.
    var alpha_gap = 1;
    var alpha;

    // alignment matrix
    var M = [];
    for (var i = 0; i <= m; i++) {
        M.push([]);
        for (var j = 0; j <= n; j++) {
            M[i].push(0);
        }
    }

    // initialize base cases
    for (var i = 0; i <= m; i++) {
        M[i][0] = i * alpha_gap;
    }
    for (var j = 0; j <= n; j++) {
        M[0][j] = j * alpha_gap;
    }

    // fill in matrix
    // NOTE:	matrix count starts at 1 for the words (0 = gap), 
    //			BUT array indices start at 0!!
    for (var i = 1; i <= m; i++) {
        for (var j = 1; j <= n; j++) {
            // do the words at this position match?
            // NOTE:  "-1" is to adjust the array index!
            alpha = checkMatch(traineeWords[i - 1], correctWords[j - 1]);
            // calculate optimal matrix entry
            M[i][j] = Math.min(M[i - 1][j - 1] + alpha, M[i - 1][j] + alpha_gap, M[i][j - 1] + alpha_gap);
        }
    }

    // backtrack through alignment matrix to find optimal alignment, 
    // and highlight mismatching words
    // NOTE:  highlightWords = [highlightCorrectWords, highlightTraineeWords]
    var highlightWords = backtrackAlignment(M, traineeWords, correctWords, alpha_gap);

    // check if alignment was perfect <==> M[m][n] = 0
    var isTXcorrect = false;
    if (M[m][n] == 0) {
        isTXcorrect = true;
    }

    return [highlightWords, isTXcorrect];
}

// function for returning the (mis)match penalty
function checkMatch(word1, word2) {
    // define penalties for match/mismatch
    var alpha_mismatch = 1;
    var alpha_match = 0;
    // compare...
    if (word1 == word2) {
        return alpha_match;
    }
    else {
        return alpha_mismatch;
    }
}

// function for backtracking through the alignment matrix to find the optimal alignment
function backtrackAlignment(M, traineeWords, correctWords, alpha_gap) {

    // get transcription lengths and initialize matrix indices for backtracking
    var m = traineeWords.length;
    var n = correctWords.length;
    var i = m;
    var j = n;
    var alpha;

    // keep track of which words to highlight as incorrect
    var highlightTraineeWords = [];
    for (var i = 0; i < m; i++) {
        highlightTraineeWords.push(false);
    }
    var highlightCorrectWords = [];
    for (var j = 0; j < n; j++) {
        highlightCorrectWords.push(false);
    }

    while (i > 0 && j > 0) {
        // CASE 1:  "diagonal" alignment with no gaps
        alpha = checkMatch(traineeWords[i - 1], correctWords[j - 1]);
        if (M[i][j] == M[i - 1][j - 1] + alpha) {
            // highlight mismatching words
            if (alpha == 1) {
                highlightTraineeWords[i - 1] = true;
                highlightCorrectWords[j - 1] = true;
            }
            // go to next element diagonally
            i--;
            j--;
        }
            // CASE 2:  "vertical" alignment with gap in correct transcription
        else if (M[i][j] == M[i - 1][j] + alpha_gap) {
            // highlight "extra" word in trainee transcription
            highlightTraineeWords[i - 1] = true;
            // go to next element "vertically"
            i--;
        }
            // CASE 3:  "horizontal" alignment with gap in trainee transcription
        else if (M[i][j] == M[i][j - 1] + alpha_gap) {
            // highlight "extra" word in correct transcription
            highlightCorrectWords[j - 1] = true;
            // go to next element "horizontally"
            j--;
        }
    }

    // highlight remaining "extra" words after the loop terminates
    if (i == 0) {
        for (var x = 0; x < j; x++) {
            highlightCorrectWords[x] = true;
        }
    }
    else {
        for (var x = 0; x < i; x++) {
            highlightTraineeWords[x] = true;
        }
    }

    return [highlightCorrectWords, highlightTraineeWords];
}
/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.StringUtils;
import com.its.modules.goods.entity.SkuKey;
import com.its.modules.goods.dao.SkuKeyDao;
import com.its.modules.goods.entity.SkuValue;
import com.its.modules.goods.dao.SkuValueDao;

/**
 * 商品规格名称Service
 * 
 * @author liuhl
 * @version 2017-07-04
 */
@Service
@Transactional(readOnly = true)
public class SkuKeyService extends CrudService<SkuKeyDao, SkuKey> {

    @Autowired
    private SkuValueDao skuValueDao;

    public SkuKey get(String id) {
        SkuKey skuKey = super.get(id);
        skuKey.setSkuValueList(skuValueDao.findList(new SkuValue(skuKey)));
        return skuKey;
    }

    public List<SkuKey> findList(SkuKey skuKey) {
        return super.findList(skuKey);
    }

    /**
     * 取得规格名称与项目
     * 
     * @param skuKey 检索条件
     * @return 规格名称与项目LIST
     */
    public List<SkuKey> findSkuKeyAndValueList(SkuKey skuKey) {
    	List<SkuKey> skuKeyList = super.findList(skuKey);
    	for (SkuKey skuKeyinList : skuKeyList) {
            // 取得对应的规格项目
    		skuKeyinList.setSkuValueList(skuValueDao.findList(new SkuValue(skuKeyinList)));
    	}
        return skuKeyList;
    }
    
    public Page<SkuKey> findPage(Page<SkuKey> page, SkuKey skuKey) {
        // 录入时Sort_Order为升序录入，须与画面保持一致
        Page<SkuKey> pageList = super.findPage(page, skuKey);
        // 最大索引
        int maxIndex = 0;
        // 取得对应的规格项目
        for (SkuKey skuKeyinList : pageList.getList()) {
            skuKeyinList.setSkuValueList(skuValueDao.findList(new SkuValue(skuKeyinList)));
            if (skuKeyinList.getSkuValueList().size() > maxIndex) {
                maxIndex = skuKeyinList.getSkuValueList().size();
            }
        }

        // LIST整形，所有LIST长度保持一致
        for (SkuKey skuKeyinList : pageList.getList()) {
            if(skuKeyinList.getSkuValueList().size() < maxIndex) {
                for (int i= maxIndex - skuKeyinList.getSkuValueList().size();i != 0;i--) {
                    skuKeyinList.getSkuValueList().add(new SkuValue());
                }
            }
        }
        
        return pageList;
    }

    @Transactional(readOnly = false)
    public void save(SkuKey skuKey) {
        super.save(skuKey);
        for (SkuValue skuValue : skuKey.getSkuValueList()) {
            if (skuValue.getId() == null) {
                continue;
            }
            if (SkuValue.DEL_FLAG_NORMAL.equals(skuValue.getDelFlag())) {
                if (StringUtils.isBlank(skuValue.getId())) {
                    skuValue.setSkuKeyId(skuKey);
                    skuValue.preInsert();
                    skuValue.getId().length();
                    skuValueDao.insert(skuValue);
                } else {
                    skuValue.preUpdate();
                    skuValueDao.update(skuValue);
                }
            } else {
                skuValueDao.delete(skuValue);
            }
        }
    }

    @Transactional(readOnly = false)
    public void delete(SkuKey skuKey) {
        super.delete(skuKey);
        skuValueDao.delete(new SkuValue(skuKey));
    }

}
package com.its.modules.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.modules.app.bean.GoodsSkuBean;
import com.its.modules.app.dao.GoodsInfoDao;
import com.its.modules.app.dao.GoodsSkuPriceDao;
import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.GoodsInfo;
import com.its.modules.app.entity.SortInfo;

/**
 * 商品信息Service
 * 
 * @author like
 * @version 2017-07-05
 */
@Service
@Transactional(readOnly = true)
public class GoodsInfoService extends CrudService<GoodsInfoDao, GoodsInfo> {

	@Autowired
	private GoodsSkuPriceDao goodsSkuPriceDao;

	public GoodsInfo get(String id) {
		GoodsInfo goodsInfo = super.get(id);
		return goodsInfo;
	}

	public List<GoodsInfo> findList(GoodsInfo goodsInfo) {
		return super.findList(goodsInfo);
	}

	public Page<GoodsInfo> findPage(Page<GoodsInfo> page, GoodsInfo goodsInfo) {
		return super.findPage(page, goodsInfo);
	}

	@Transactional(readOnly = false)
	public void save(GoodsInfo goodsInfo) {
		super.save(goodsInfo);
	}

	@Transactional(readOnly = false)
	public void update(GoodsInfo goodsInfo) {
		dao.update(goodsInfo);
	}

	@Transactional(readOnly = false)
	public void delete(GoodsInfo goodsInfo) {
		super.delete(goodsInfo);
	}

	/**
	 * 获取每个商家推荐的3个商品
	 * 
	 * @param businessInfoList
	 * @return
	 */
	public List<GoodsInfo> getBusinessRecomendGoodsList(List<BusinessInfo> businessInfoList) {
		return dao.getBusinessRecomendGoodsList(businessInfoList);
	}

	/**
	 * 获取商家商品分类集合
	 * 
	 * @param businessInfoID
	 * @return
	 */
	public List<SortInfo> getBusinessSortInfoList(String businessInfoID) {
		return dao.getBusinessSortInfoList(businessInfoID);
	}

	/**
	 * 获取某一分类的商品集合
	 * 
	 * @param SortInfoID
	 * @param businessInfoID
	 * @return
	 */
	public List<GoodsInfo> getGoodsInfoBySortList(String sortInfoID, String businessInfoID) {
		return dao.getGoodsInfoBySortList(sortInfoID, businessInfoID);
	}

	/**
	 * 返回商品规格集合
	 * 
	 * @param goodsInfoID
	 * @return
	 */
	public List<GoodsSkuBean> getGoodsSkuList(String goodsInfoID) {
		return goodsSkuPriceDao.getGoodsSkuList(goodsInfoID);
	}

	/**
	 * 商品名称=商品名称+商品单位名称+限购数（商品无限购则不显示）
	 * 
	 * @param info
	 *            商品对象
	 * @return
	 */
	public String getGoodsInfoName(GoodsInfo info) {
		StringBuffer name = new StringBuffer(info.getName());
		String unit = "";
		// 服务单位是否来自公共字典表:0否1是
		if ("1".equals(info.getIsBase())) {
			unit = dao.getUnitNameSystem(info.getBaseUnitId());
		} else if ("0".equals(info.getIsBase())) {
			unit = dao.getUnitNameCustom(info.getBaseUnitId());
		}
		if (StringUtils.isNotBlank(unit)) {
			name.append("/").append(unit);
		}
		if ("1".equals(info.getQuota())) {
			name.append("(限购").append(info.getQuotaNum()).append(unit).append(")");
		}
		return name.toString();
	}

	/**
	 * 减少商品库存
	 * 
	 * @param goodsInfoId
	 * @param goodsSkuPriceId
	 * @param reduce
	 * @return
	 */
	public int reduceGoodsInfoStock(String goodsInfoId, String goodsSkuPriceId, int reduce) {
		int record = dao.reduceGoodsInfoStock(goodsInfoId, reduce);
		if (StringUtils.isNotBlank(goodsSkuPriceId)) {
			record = dao.reduceGoodsSkuPriceStock(goodsSkuPriceId, reduce);
		}
		return record;
	}

	/**
	 * 返回完整的商品图片路径
	 * 
	 * @param businessId
	 * @param goods
	 * @return
	 */
	public String getGoodsPicUrl(GoodsInfo goods, HttpServletRequest request) {
		if (StringUtils.isBlank(goods.getImgs())) {
			return "";
		}
		String url = goods.getImgs().split(",")[0];
		return MyFDFSClientUtils.get_fdfs_file_url(request, url + "_compress2");
	}

	/**
	 * 返回商品所有图片的集合
	 * 
	 * @param goods
	 * @return
	 */
	public List<Map<String, Object>> getAllGoodsPicUrlMap(GoodsInfo goods, HttpServletRequest request) {
		List<Map<String, Object>> list = new ArrayList<>();
		if (StringUtils.isBlank(goods.getImgs())) {
			return list;
		}
		String[] pics = goods.getImgs().split(",");
		for (int i = 0; i < pics.length; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("url", MyFDFSClientUtils.get_fdfs_file_url(request, pics[i] + "_compress2"));
			list.add(map);
		}
		return list;
	}

	/**
	 * 更新商品库存，已售数量
	 * 
	 * @param count
	 *            操作数量
	 * @param goodsInfoId
	 *            商品ID
	 * @return 操作的行数
	 */
	public int updateStockAndSellCount(int count, String goodsInfoId) {
		return dao.updateStockAndSellCount(count, goodsInfoId);
	}
}
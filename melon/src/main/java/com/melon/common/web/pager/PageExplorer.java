package com.melon.common.web.pager;

public interface PageExplorer {

	/**
	 * JSP�뿉�꽌 Paging 寃곌낵瑜� 蹂댁뿬以��떎.
	 * getPagingList�뒗 &lt;form> �깭洹� �븞�뿉 �옉�꽦�릺�뼱�빞 �븳�떎.
	 * @param link Page 踰덊샇瑜� �쟾�떖�븷 Parameter Name
	 * @param pageFormat Page 踰덊샇瑜� 蹂댁뿬以� �뙣�꽩 @(at)媛� �럹�씠吏� 踰덊샇濡� 移섑솚�맂�떎. [@]濡� �옉�꽦�븷 寃쎌슦 [1] [2] [3] 泥섎읆 蹂댁뿬吏꾨떎.
	 * @param prev �씠�쟾 �럹�씠吏� 洹몃９�쑝濡� 媛��뒗 踰꾪듉�쓽 �씠由꾩쓣 �옉�꽦�븳�떎.
	 * @param next �떎�쓬 �럹�씠吏� 洹몃９�쑝濡� 媛��뒗 踰꾪듉�쓽 �씠由꾩쓣 �옉�꽦�븳�떎.
	 * @param formId �꽌踰꾩뿉寃� �쟾�떖�맆 Form �쓽 �븘�씠�뵒瑜� �옉�꽦�븳�떎.
	 * @return
	 */
	public String getPagingList(String link, String pageFormat, String prev, String next, String formId);
	
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TDB2C.Model.ViewModel
{

    #region 列表页返回的商品数据
    /// <summary>
    /// ProductModel in 列表页
    /// </summary>
    public class Product_in_list
    {
        /// <summary>
        /// 无参构造函数
        /// </summary>
        public Product_in_list()
        {
            this.id=0;
            this.channel_id=0;
            this.category_id=0;
            this.goods_id=0;
            this.name="";
            this.title="";
            this.brand_id=0;
            this.color_id=0;
            this.capacity_id=0;
            this.memorysize_id=0;
            this.network_id=0;
            this.brand_name="";
            this.color_name="";
            this.capacity_name="";
            this.memorysize_name="";
            this.network_name="";
            this.point=0;
            this.add_time=DateTime.Now;
            this.sort_id=0;
            this.market_price=0m;
            this.seal_price=0m;
            this.supplier_price=0m;
            this.wholesale_price=0m;
            this.cost_point=0;
            this.cost_nfc=0m;
            this.promotion_logo="";
            this.support_service="";
            this.configure_introduct="";
            this.miaoshu="";
            this.freebie_introduct="";
            this.sales=0;
            this.comments=0;
            this.clicks=0;
            this.shelves_time=DateTime.Now;
            this.pic="";
            this.pics="";
            this.description="";
            this.stock_headquarters=0;
            this.stock_distributor_all=0;
        }
        /// <summary>
        /// 主键Id
        /// </summary>
        public int id { get; set; }
        /// <summary>
        /// 频道Id
        /// </summary>
        public int channel_id { get; set; }
        /// <summary>
        /// 所属分类
        /// </summary>
        public int category_id { get; set; }
        /// <summary>
        /// 产品Id
        /// </summary>
        public int goods_id { get; set; }
        /// <summary>
        /// 系统名称(由颜色、网络制式、容量、内存)组成
        /// </summary>
        public string name { get; set; }
        /// <summary>
        /// 商品别名
        /// </summary>
        public string title { get; set; }
        /// <summary>
        /// 品牌Id
        /// </summary>
        public int brand_id { get; set; }
        /// <summary>
        /// 颜色Id
        /// </summary>
        public int color_id { get; set; }
        /// <summary>
        /// 容量Id
        /// </summary>
        public int capacity_id { get; set; }
        /// <summary>
        /// 内存Id
        /// </summary>
        public int memorysize_id { get; set; }
        /// <summary>
        /// 网络制式Id
        /// </summary>
        public int network_id { get; set; }
        /// <summary>
        /// 品牌
        /// </summary>
        public string brand_name { get; set; }
        /// <summary>
        /// 颜色
        /// </summary>
        public string color_name { get; set; }
        /// <summary>
        /// 容量（ROM）
        /// </summary>
        public string capacity_name { get; set; }
        /// <summary>
        /// 内存（RAM）
        /// </summary>
        public string memorysize_name { get; set; }
        /// <summary>
        /// 网络制式
        /// </summary>
        public string network_name { get; set; }
        /// <summary>
        /// 积分
        /// </summary>
        public int point { get; set; }
        /// <summary>
        /// 添加时间
        /// </summary>
        public DateTime add_time { get; set; }
        /// <summary>
        /// 排序
        /// </summary>
        public int sort_id { get; set; }
        /// <summary>
        /// 市场价
        /// </summary>
        public decimal market_price { get; set; }
        /// <summary>
        /// 销售价
        /// </summary>
        public decimal seal_price { get; set; }
        /// <summary>
        /// 供应商价格
        /// </summary>
        public decimal supplier_price { get; set; }
        /// <summary>
        /// 批发商价格
        /// </summary>
        public decimal wholesale_price { get; set; }
        /// <summary>
        /// 兑换所需积分
        /// </summary>
        public int cost_point { get; set; }
        /// <summary>
        /// 兑换所需移动和包积分
        /// </summary>
        public decimal cost_nfc { get; set; }
        /// <summary>
        /// 促销图标
        /// </summary>
        public string promotion_logo { get; set; }
        /// <summary>
        /// 支持服务
        /// </summary>
        public string support_service { get; set; }
        /// <summary>
        /// 标配信息
        /// </summary>
        public string configure_introduct { get; set; }
        /// <summary>
        /// 商品描述
        /// </summary>
        public string miaoshu { get; set; }
        /// <summary>
        /// 赠品介绍
        /// </summary>
        public string freebie_introduct { get; set; }
        /// <summary>
        /// 销量
        /// </summary>
        public int sales { get; set; }
        /// <summary>
        /// 评论量
        /// </summary>
        public int comments { get; set; }
        /// <summary>
        /// 点击量
        /// </summary>
        public int clicks { get; set; }
        /// <summary>
        /// 上架时间
        /// </summary>
        public DateTime shelves_time { get; set; }
        /// <summary>
        /// 颜色小图
        /// </summary>
        public string pic { get; set; }
        /// <summary>
        /// 颜色组图(以英文；进行分割)
        /// </summary>
        public string pics { get; set; }
        /// <summary>
        /// 描述简介
        /// </summary>
        public string description { get; set; }
        /// <summary>
        /// 平台库存
        /// </summary>
        public int stock_headquarters { get; set; }
        /// <summary>
        /// 分销商总库存
        /// </summary>
        public int stock_distributor_all { get; set; }

    }
    #endregion

    #region 列表页获取筛选项 相关================
    /// <summary>
    /// 返回筛选项
    /// </summary>
    public class ReturnCategoryFilter
    {
        /// <summary>
        /// 有参构造函数
        /// </summary>
        /// <param name="title"></param>
        /// <param name="list_filter"></param>
        public ReturnCategoryFilter(string title, List<Model.ViewModel.FilterCategory> list_filter)
        {
            this._title=title;
            this._filter=list_filter;
        }
        private string _title="";
        /// <summary>
        /// 名称
        /// </summary>
        public string Title
        {
            get { return _title; }
            set { _title=value; }
        }
        private List<Model.ViewModel.FilterCategory> _filter=new List<Model.ViewModel.FilterCategory>();
        /// <summary>
        /// 筛选详细
        /// </summary>
        public List<Model.ViewModel.FilterCategory> Filter
        {
            get { return _filter; }
            set { _filter=value; }
        }
    }
    /// <summary>
    /// 列表页筛选项
    /// </summary>
    public class FilterCategory
    {
        public FilterCategory()
        {
            this.id=0;
            this.title="";
        }
        /// <summary>
        /// 主键
        /// </summary>
        public int id { get; set; }
        /// <summary>
        /// 名称
        /// </summary>
        public string title { get; set; }
    }

    #endregion

    #region 列表页,返回价格区间参数
    public class Price_range
    {
        public Price_range()
        {
            this.id=0;
            this.title="";
            this.min_value=0;
            this.max_value=0;
        }

        /// <summary>
        /// 主键Id
        /// </summary>
        public int id { get; set; }
        /// <summary>
        /// 标题
        /// </summary>
        public string title { get; set; }
        /// <summary>
        /// 最小值
        /// </summary>
        public int min_value { get; set; }
        /// <summary>
        /// 最大值
        /// </summary>
        public int max_value { get; set; }
    }
    #endregion
}

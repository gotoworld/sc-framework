var tag = {
    page: function (pageInfo) {
        tag.page(pageInfo, 'page');
    }
    , page: function (pageInfo, func) {
        tag.page(pageInfo, func, null)
    }
    , page: function (pageInfo, func, param) {
        var getPageFunc = function (pageNum) {
            if(!func)func='page';
            if (param) {
                return func + "(" + pageNum + "," + param + ")";
            } else {
                return func + "(" + pageNum + ")";
            }
        }
        var HTML_3DOT_STRING = "<a class=\"dot\">...</a>";
        if (!(pageInfo)) {
            return HTML_3DOT_STRING;
        }
        /** 当前页码 */
        var currentPageNo = pageInfo.pageIndex;
        /** 总页数 */
        var pageCount = pageInfo.pageCount;
        /** 总纪录数 */
        var dataCount = pageInfo.dataCount;

        /** 是否存在后一条记录 */
        var hasNext = (pageInfo.pageIndex >= pageInfo.pageCount) ? false : true;
        /** 是否存在前一条记录 */
        var hasPrevious = pageInfo.pageIndex <= 1 ? false : true;
        /** 画面显示的页码数量 */
        var pageNoCount = 15;

        // 上一页
        var prePage = "";
        if (hasPrevious) {
            prePage += ("<a href=\"javascript:;\" ng-click=\"" + getPageFunc(pageInfo.prePage) + "\">");
            prePage += ("上一页");
            prePage += ("</a>");
        } else {
            prePage += ("<a class=\"disabled\">上一页</a>");
        }


        // 下一页
        var nextPage = "";
        if (hasNext) {
            nextPage += ("<a href=\"javascript:;\" ng-click=\"" + getPageFunc(pageInfo.nextPage) + "\">");
            nextPage += ("下一页");
            nextPage += ("</a>");
        } else {
            nextPage += ("<a class=\"disabled\">下一页</a>");
        }
        var tempPageCount=Math.ceil(pageCount/pageNoCount);
        var tempPageNo=Math.ceil(currentPageNo/pageNoCount);
        console.info(tempPageCount)//分页条变化次数
        console.info(tempPageNo)//分页条变化次数

        // 中间页
        var midPage = "";

        // 总页数<=显示页数
        if (pageCount >= 1 && pageCount <= pageNoCount) {
            for (var i = 1; i <= pageCount; i++) {
                if (currentPageNo == i) {
                    midPage += ("<a class=\"on\">" + i + "</a>");
                } else {
                    midPage += ("<a href=\"javascript:;\" ng-click=\"" + getPageFunc(i) + "\">");
                    midPage += (i);
                    midPage += ("</a>");
                }
            }
        } else if (pageCount > pageNoCount) {// 总页数>显示页数
            var i = 1;
            if(tempPageNo>=1) i=(tempPageNo-1)*pageNoCount+1;
            var HTML_3DOT_STRING_FLAG=false;
            for (;i <= tempPageNo*pageNoCount && i<=pageCount; i++) {
                if(tempPageNo>1 && !HTML_3DOT_STRING_FLAG){
                    // 加上首页数字
                    midPage += "<a href=\"javascript:;\" ng-click=\"" + getPageFunc(1) + "\">";
                    midPage += (1);
                    midPage += ("</a>");
                    // 加上...
                    midPage += (HTML_3DOT_STRING);
                    HTML_3DOT_STRING_FLAG=true;
                }
                if (currentPageNo == i) {
                    midPage += ("<a class=\"on\">" + i + "</a>");
                } else {
                    midPage += ("<a href=\"javascript:;\" ng-click=\"" + getPageFunc(i) + "\">");
                    midPage += (i);
                    midPage += ("</a>");
                }
                if (i == tempPageNo*pageNoCount) {
                    // 加上...
                    midPage += (HTML_3DOT_STRING);
                    // 加上末页数字
                    midPage += ("<a href=\"javascript:;\" ng-click=\"" + getPageFunc(pageInfo.pageCount) + "\">");
                    midPage += (pageCount);
                    midPage += ("</a>");
                    break;
                }
            }
        } else { // 没有数据
            midPage += HTML_3DOT_STRING;
        }
        // 汇总输出
        var pageCom = "";
        pageCom += ("<a class=\"disabled\">共" + dataCount + "条</a>");
        // pageCom += ("<div class=\"b_page\">");
        pageCom += (prePage);
        pageCom += (midPage);
        pageCom += (nextPage);
        pageCom += ("<a class=\"disabled\">共" + pageInfo.pageCount+"页</a>");
        pageCom += ("<div class=\"clear\"></div>");
        // pageCom += ("</div>");

        return pageCom;
    }
}
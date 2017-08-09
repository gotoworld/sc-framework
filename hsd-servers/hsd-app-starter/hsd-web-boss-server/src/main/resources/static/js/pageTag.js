var tag= {
    page:function (pageInfo) {
        var HTML_3DOT_STRING = "<a class=\"dot\">...</a>";
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
            prePage += ("<a href=\"javascript:;\" ng-click=\"page(" + pageInfo.prePage + ")\">");
            prePage += ("上一页");
            prePage += ("</a>");
        } else {
            prePage += ("<a class=\"disabled\">上一页</a>");
        }


        // 下一页
        var nextPage = "";
        if (hasNext) {
            nextPage += ("<a href=\"javascript:;\" ng-click=\"page(" + pageInfo.nextPage + ")\">");
            nextPage += ("下一页");
            nextPage += ("</a>");
        } else {
            nextPage += ("<a class=\"disabled\">下一页</a>");
        }


        // 中间页
        var midPage = "";

        // 只有一页数据
        if (pageCount == 1) {
            midPage += ("<a class=\"on\">1</a>");

            // 总页数<=显示页数
        } else if (pageCount > 1 && pageCount <= pageNoCount) {
            for (var i = 1; i <= pageCount; i++) {
                if (currentPageNo == i) {
                    midPage += ("<a class=\"on\">" + i + "</a>");
                } else {
                    midPage += ("<a href=\"javascript:;\" ng-click=\"page(" + i + ")\">");
                    midPage += (i);
                    midPage += ("</a>");
                }
            }

            // 总页数>显示页数
        } else if (pageCount > pageNoCount) {

            // 当前页数在起始显示页数内
            if (currentPageNo <= pageNoCount) {
                for (var i = 1; i <= pageCount; i++) {
                    if (currentPageNo == i) {
                        midPage += ("<a class=\"on\">" + i + "</a>");
                    } else {
                        midPage += ("<a href=\"javascript:;\" ng-click=\"page(" + i + ")\">");
                        midPage += (i);
                        midPage += ("</a>");
                    }
                    if (i == pageNoCount) {
                        // 加上...
                        midPage += (HTML_3DOT_STRING);
                        // 加上末页数字
                        midPage += ("<a href=\"javascript:;\" ng-click=\"page(" + pageInfo.pageCount + ")\">");
                        midPage += (pageCount);
                        midPage += ("</a>");
                        break;
                    }
                }

                // 当前页数在末页显示页数内
            } else if (currentPageNo > (pageCount - pageNoCount)) {

                // 加上首页数字
                midPage += "<a href=\"javascript:;\" ng-click=\"page(1)\">";
                midPage += (1);
                midPage += ("</a>");
                // 加上...
                midPage += (HTML_3DOT_STRING);

                for (var i = (pageCount - pageNoCount + 1); i <= pageCount; i++) {
                    if (currentPageNo == i) {
                        midPage += ("<a class=\"on\">" + i + "</a>");
                    } else {
                        midPage += ("<a href=\"javascript:;\" ng-click=\"page(" + i + ")\">");
                        midPage += (i);
                        midPage += ("</a>");
                    }
                }
            } else {
                // 加上首页数字
                midPage += "<a href=\"javascript:;\" ng-click=\"page(1)\">";
                midPage += (1);
                midPage += ("</a>");
                // 加上...
                midPage += (HTML_3DOT_STRING);
                for (var i = currentPageNo; i < (currentPageNo + pageNoCount); i++) {
                    if (currentPageNo == i) {
                        midPage += ("<a class=\"on\">" + i + "</a>");
                    } else {
                        midPage += ("<a href=\"javascript:;\" ng-click=\"page(" + i + ")\">");
                        midPage += (i);
                        midPage += ("</a>");
                    }
                }
                // 加上...
                midPage += (HTML_3DOT_STRING);
                // 加上末页数字
                midPage += ("<a href=\"javascript:;\" ng-click=\"page(" + pageInfo.pageCount + ")\">");
                midPage += (pageCount);
                midPage += ("</a>");
            }
            // 没有数据
        } else {
            midPage += ("");
        }
        // 汇总输出
        var pageCom = "";
        // pageCom += ("<div class=\"b_page\">");
        pageCom += (prePage);
        pageCom += (midPage);
        pageCom += (nextPage);
        pageCom += ("<a class=\"disabled\">共" + dataCount + "条</a>");
        pageCom += ("<div class=\"clear\"></div>");
        // pageCom += ("</div>");

        return pageCom;
    }
}
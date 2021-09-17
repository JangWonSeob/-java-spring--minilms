package ko.co.dongyang.study.minilms.util;

import org.springframework.data.domain.Page;

public class PageUtil {

    // 전체 개수
    private long totalCount;

    // 한 페이지에 나오는 개수(한 페이지에 나오는 게시물 수)
    private long pageSize = 10;

    // 페이지블럭 개수(페이지 번호 수) ex) << < 1, 2, 3, 4, 5 > >>
    private long pageBlockSize = 10;

    // 현재 페이지 번호
    private long pageIndex;

    // 전체 블럭
    private long totalBlockCount;

    // 시작 페이지
    private long startPage;

    // 종료 페이지
    private long endPage;

    // 페이지 이동시 전달되는 파라미터(쿼리스트링)
    private String queryString;

    /*
    전체 페이지 수 : 156
    현페이지에 나오는 갯수 : 10
    페이지 블럭 : 10
    현제 페이지 번호!!!
        --> 전체페이지블럭개수 구해야함
        --> 시작페이지번호 구해야함
        --> 종료페이지번호 구해야함
     */

    public PageUtil(long totalCount, long pageIndex, String queryString) {
        this.totalCount = totalCount;
        this.pageIndex = pageIndex;
        this.queryString = queryString;
    }

    public PageUtil(long totalCount, long pageSize, String queryString, long pageBlockSize, long pageIndex) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageBlockSize = pageBlockSize;
        this.pageIndex = pageIndex;
    }

    public String pager() {
        init();

//        System.out.println("************페이지 출력************");
//        System.out.println("한 페이지 개수 : " + pageSize + " / 페이지블럭 개수 : " + pageBlockSize);
//        System.out.println("현제 페이지 / 전체 블록 " + String.format("%d/%d (%d)", pageIndex, totalBlockCount, totalCount));
//        System.out.println("시작 페이지 / 종료 페이지 " + String.format("%d - %d", startPage, endPage));

        StringBuilder sb = new StringBuilder();

        //  << < 1 2 3 4 5 6 7 8 9 10 > >>

        long previousPageIndex = startPage > 1 ? startPage - 1 : 1;
        long nextPageIndex = endPage < totalBlockCount ? endPage + 1 : totalBlockCount;

        String addQueryString = "";
        if (queryString != null && queryString.length() > 0) {
            addQueryString = "&" + queryString;
        }

        // 맨 처음으로 이동
        sb.append(String.format("<a href='?pageIndex=%d%s'>&lt;&lt;</a>", 1, addQueryString));
        sb.append(System.lineSeparator());
        sb.append(String.format("<a href='?pageIndex=%d%s'>&lt;</a>", previousPageIndex, addQueryString));
        sb.append(System.lineSeparator());

        for (long i = startPage; i <= endPage; i++) {
            if (i == pageIndex) {
                sb.append(String.format("<a class='on' href='?pageIndex=%d%s'>%d</a>", i, addQueryString, i));
            } else {
                sb.append(String.format("<a href='?pageIndex=%d%s'>%d</a>", i, addQueryString, i));
            }
            sb.append(System.lineSeparator());

        }

        sb.append(String.format("<a href='?pageIndex=%d%s'>&gt;</a>", nextPageIndex, addQueryString));
        sb.append(System.lineSeparator());
        sb.append(String.format("<a href='?pageIndex=%d%s'>&gt;&gt;</a>", totalBlockCount, addQueryString));
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    private void init() {

        // 현재 페이지가 0 인 경우 처리
        if (pageIndex < 1) {
            pageIndex = 1;
        }

        // 페이지 사이즈가 0 인 경우 처리리
        if (pageSize < 1) {
            pageSize = 1;
        }

        // totalCount => 127
        // pageSize   => 10
        // 13
        totalBlockCount = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);

        // pageIndex
        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10                -> 1
        // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9                 위에꺼 -1
        // 0, 0, 0, 0, 0, 0, 0, 0, 0, 0                 위에꺼에 pageSize 나눈 값
        // 0, 0, 0, 0, 0, 0, 0, 0, 0, 0                 위에꺼 곱하기 pageSize
        // 1, 1, 1, 1, 1, 1, 1, 1, 1, 1                 + 1

        // 11, 12, 13, 14 ,15, 16, 17, 18, 19, 20       -> 11
        // 10, 11, 12, 13, 14, 15, 16, 17, 18, 19       위에꺼 -1
        // 1, 1, 1, 1, 1, 1, 1, 1, 1, 1                 위에꺼에 pageSize 나눈 값
        // 10, 10, 10, 10, 10, 10, 10, 10, 10, 10       위에꺼 곱하기 pageSize
        // 11, 11, 11, 11, 11, 11, 11, 11, 11, 11       + 1

        // 1, 2, 3, 4, 5                -> 1
        // 6, 7, 8, 9, 10               -> 6
        // 11, 12, 13, 14, 15           -> 11
        // 16, 17, 18, 19, 20            -> 16

        if (pageIndex > totalBlockCount) {
            pageIndex = totalBlockCount;
        }

        startPage = ((pageIndex - 1) / pageBlockSize) * pageBlockSize + 1;

        endPage = startPage + pageBlockSize - 1;
        if (endPage > totalBlockCount) {
            endPage = totalBlockCount;
        }

    }
}

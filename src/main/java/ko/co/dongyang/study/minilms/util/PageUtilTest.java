package ko.co.dongyang.study.minilms.util;

public class PageUtilTest {

    public static void main(String[] args){



        PageUtil pageUtil = new PageUtil(151, 10,"");
        String htmlPager = pageUtil.pager();

        System.out.println(htmlPager);

    }


}

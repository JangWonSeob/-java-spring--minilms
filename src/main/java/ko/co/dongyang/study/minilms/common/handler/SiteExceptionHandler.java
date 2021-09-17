package ko.co.dongyang.study.minilms.common.handler;

import ko.co.dongyang.study.minilms.common.exception.BizException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
@RestControllerAdvice
public class SiteExceptionHandler {

    @ExceptionHandler(value = {BizException.class})
    public void bizExceptionHandler(BizException exception, HttpServletResponse response) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<script>alert(" + exception.getMessage() + ");</script>");
        sb.append("<script>location.history.back(-1);</script>");
        sb.append("</head>");
        sb.append("</html>");

        PrintWriter printWriter = response.getWriter();
        printWriter.write(sb.toString());
        printWriter.close();

    }

}

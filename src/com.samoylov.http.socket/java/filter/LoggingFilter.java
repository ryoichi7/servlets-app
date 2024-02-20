package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

@WebFilter("/*")
public class LoggingFilter implements Filter {
    private static final String BASE_PATH = "/Users/ryoichi/Desktop/http-servlet-starter/logs/";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var userAgent = ((HttpServletRequest) servletRequest).getHeader("User-Agent");
        var method = ((HttpServletRequest) servletRequest).getMethod();
        var fullPath = Path.of(BASE_PATH + LocalDate.now() + ".txt");

        var log = "Method: %s; User-Agent: %s; timestamp: %d;%s"
                .formatted(method, userAgent, System.currentTimeMillis(), System.lineSeparator());
        try {
            Files.write(fullPath, log.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ignored) {
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}

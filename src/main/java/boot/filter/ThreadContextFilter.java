package boot.filter;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Component
public class ThreadContextFilter implements Filter {
    private static final String UUID_FIELD = "uuid";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            ThreadContext.put(UUID_FIELD, UUID.randomUUID().toString());
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            ThreadContext.remove(UUID_FIELD);
        }
    }
}

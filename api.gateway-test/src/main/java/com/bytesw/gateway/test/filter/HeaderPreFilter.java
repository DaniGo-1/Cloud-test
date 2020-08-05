package com.bytesw.gateway.test.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component
public class HeaderPreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext(); //Request context pasa información entre filtros, HttpServletRequest y HttpServletResponse se almacenan ahí
        HttpServletRequest request = context.getRequest();
        String header = request.getHeader("school");
        if(header == null || header.isEmpty()){
            throw new ZuulException("school no encontrado",401, "school no encontrado");
        }
        System.out.println("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
        return null;
    }
}

package br.com.wplex.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	private static final String XF_SIGNATURE = "eC^PCK#&W:eS<Un]k8n4sJf*)a_AnT,";
	private static final String XF_CLIENT_SECRET = "cL13n7-S3cR3T-gra56Asd";
	private static final String XF_API_KEY = "CEjbzb#T_sj%4>8'rZZL3^SEd5UXXpj";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String providerSignature = request.getHeader("xf-provider-signature");

		if (StringUtils.isBlank(providerSignature) || !XF_SIGNATURE.equals(providerSignature)) {
			System.out.println("BadCredentialsException - provider-signature not setup.");
			return false;
		}

		String clientSecret = request.getHeader("xf-client-secret");
		String apiKey = request.getHeader("xf-api-key");

		if (StringUtils.isBlank(clientSecret) || StringUtils.isBlank(apiKey)) {
			System.out.println("BadCredentialsException - client-secret and api-key not setup");
			return false;
		}

		if (!XF_CLIENT_SECRET.equals(clientSecret) || !XF_API_KEY.equals(apiKey)) {
			System.out.println("BadCredentialsException - client-secret and api-key incorret.");
			return false;
		}

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}

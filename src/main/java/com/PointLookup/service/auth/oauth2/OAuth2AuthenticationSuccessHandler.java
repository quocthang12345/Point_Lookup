package com.PointLookup.service.auth.oauth2;

import static com.PointLookup.service.auth.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.PointLookup.service.auth.tokenProvider.JwtTokenProvider;
import com.PointLookup.service.auth.userDetail.UserPrincipal;
import com.PointLookup.util.CookiesUtil;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	    private JwtTokenProvider tokenProvider;

//	    private AppProperties appProperties;

	    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;


//	    @Autowired
//	    OAuth2AuthenticationSuccessHandler(JwtTokenProvider tokenProvider, AppProperties appProperties,
//	                                       HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
//	        this.tokenProvider = tokenProvider;
//	        this.appProperties = appProperties;
//	        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
//	    }
	    
	    @Autowired
	    OAuth2AuthenticationSuccessHandler(JwtTokenProvider tokenProvider,
	                                       HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
	        this.tokenProvider = tokenProvider;
	        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
	    }
	    
	    
	    /* 
	     * hàm onAuthenticationSuccessc được gọi khi xác thực thành công
	     * clear attribute xác thực và cookies được lưu 
	     * */
	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
	        String targetUrl = determineTargetUrl(request, response, authentication);

	        if (response.isCommitted()) {
	            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
	            return;
	        }

	        clearAuthenticationAttributes(request, response);
	        getRedirectStrategy().sendRedirect(request, response, targetUrl);
	    }

	    /* 
	     * hàm determineTargetUrl được gọi để sinh url đích để chuyển hướng ứng dụng
	     * tạo token login dựa trên principal sau khi xác thực và được đính kèm vào làm param cho uri chuyển hướng
	     * */
	    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
	        Optional<String> redirectUri = CookiesUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
	                .map(Cookie::getValue);

//	        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
//	            throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
//	        }
	        
//	        if(redirectUri.isPresent()) {
//	            throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
//	        }

	        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

	        String token = tokenProvider.generateToken((UserPrincipal) authentication.getPrincipal());

	        return UriComponentsBuilder.fromUriString(targetUrl)
	                .queryParam("token", token)
	                .build().toUriString();
	    }

	    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
	        super.clearAuthenticationAttributes(request);
	        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
	    }

//	    private boolean isAuthorizedRedirectUri(String uri) {
//	        URI clientRedirectUri = URI.create(uri);
//
//	        return appProperties.getOauth2().getAuthorizedRedirectUris()
//	                .stream()
//	                .anyMatch(authorizedRedirectUri -> {
//	                    // Only validate host and port. Let the clients use different paths if they want to
//	                    URI authorizedURI = URI.create(authorizedRedirectUri);
//	                    if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
//	                            && authorizedURI.getPort() == clientRedirectUri.getPort()) {
//	                        return true;
//	                    }
//	                    return false;
//	                });
//	    }
}

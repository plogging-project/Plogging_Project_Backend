package city.olooe.plogging_project.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import city.olooe.plogging_project.model.MemberEntity;
import city.olooe.plogging_project.persistence.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
  private static final String LOCAL_REDIRECT_URL = "http://localhost:3000/";

  @Autowired
  private MemberRepository repository;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    TokenProvider tokenProvider = new TokenProvider();
    String userId = ((ApplicationUserPrincipal)authentication.getPrincipal()).getName();
    String token = tokenProvider.oauthCreateToken(authentication);
    MemberEntity socialMember = repository.findByUserId(userId);
    String memberNo = String.valueOf(socialMember.getMemberNo());
    String userName = socialMember.getUserName();
    String gender = URLEncoder.encode(socialMember.getGender(), "utf-8");
    String nickName = URLEncoder.encode(socialMember.getNickName(), "utf-8");
    String email = socialMember.getEmail();
    String authProvider = socialMember.getAuthProvider();

    Optional<Cookie> oCookie = Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName().equals(RedirectUrlCookieFilter.REDIRECT_URI_PARAM)).findFirst();
    Optional<String> redirectUri = oCookie.map(Cookie::getValue);

    log.info("token {}", token);
    // response.getWriter().write(token);
    // response.sendRedirect("http://localhost:3000/socialLogin?token=" + token);


    response.sendRedirect(
        redirectUri.orElseGet(() -> LOCAL_REDIRECT_URL) + "member/socialLogin?userId=" + userId + "&token=" + token 
        + "&memberNo=" + memberNo
        + "&userName=" + userName
        + "&gender=" + gender
        + "&nickName=" + nickName
        + "&email=" + email
        + "&authProvider=" + authProvider);
  }
}

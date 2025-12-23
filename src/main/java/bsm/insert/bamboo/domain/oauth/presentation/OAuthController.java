package bsm.insert.bamboo.domain.oauth.presentation;

import bsm.insert.bamboo.domain.oauth.application.service.BsmOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {

    private final BsmOAuthService bsmOAuthService;

    @GetMapping("/login")
    public ResponseEntity<Void> login() {
        String redirectUrl = bsmOAuthService.getOAuthRedirectUrl();
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(redirectUrl))
                .build();
    }

    @GetMapping("/callback")
    public ResponseEntity<Void> callback(@RequestParam String code) {
        String token = bsmOAuthService.handleCallback(code);
        String frontEndRedirectUrl = bsmOAuthService.getFrontEndRedirectUrl(token);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(frontEndRedirectUrl))
                .build();
    }
}

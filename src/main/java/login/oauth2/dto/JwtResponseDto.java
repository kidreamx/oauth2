package login.oauth2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponseDto {
    String Bearer;
    String token;
}

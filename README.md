# 로그인-jwt 이용

# 1.JWT란?

- JWT는 인증에 필요한 정보들을 암호화 시킨 JSON 토큰을 의미한다.
- JWT를 이용한 인증은 유저를 인증하고 식별하기 위한 Token 기반 인증이다.
- JWT는 JSON 데이터를 **Base64 URL-safe Encode**를 통해 인코딩하여 직렬화한 것이고,
    
    토큰 내부에는 개인키를 통한 전자서명이 들어있습니다.
    
- 토큰 기반 인증에서 서버가 클라이언트의 상태를 저장하지 않아도 되기 때문에 Stateless하게 설계가 가능하다.
- JWT를 통한 인증 순서
    1. 사용자가 로그인시 로그인 아이디, 패스워드를 담아 서버에 요청
    2. 서버에서 서명된(Signed) JWT토큰을 생성하여 클라이언트에 응답으로 변환
    3. 클라이언트는 응답으로 반환된 JWT토큰을 사용하여 요청 시 마다 HTTP Header에 JWT를 담아 요청
    4. 서버에서는 요청된 HTTP Header의 JWT를 검증하여 토큰이 유효한지 검증 후 유효하다면 요청에 맞는 응답 반환
    

# 2. JWT의 구조

- JWT는 각각의 구성요소가 .으로 구분되어 있으며, 구성 요소는 다음 세가지로 구성된다.
    - Header
    - Payload
    - Signature

1. Header
    - Header에는 보통 토큰의 타입이나, 전자서명시 어떤 알고리즘이 사용되었는지 저장한다.
2. Payload
    - Payload에서는 보통 Claim이라는 토큰에서 사용할 정보들이 담겨있다.
    - Key-Value 형식으로 이뤄지는 하나의 쌍들을 Claim이라고 한다.
    - JWT의 표준 스펙에는 7가지의 Claim이 정의되어 있습니다.
        
        1. iss(Issuer) : 토큰 발급자
        2. sub(Subjec) : 토큰 제목 - 토큰에서 사용자에 대한 식별값이 된다.
        3. aud(Audience) : 토큰 대상자
        4. exp(Expiration Time) : 토큰 만료 시간
        5. nbf(Not Before) : 토큰 활성 날짜 (이 날짜 이전의 토큰은 활성화 되지 않음을 보장)
        6. iat(Issued At) : 토큰 발급 시간
        7. jti(JWT Id) : JWT 토큰 식별자 (issuer가 여러 명일 때 구분하기 위한 값)
        
3. Signature
    - JWT 구조에서 가장 중요한 **Signature(서명)**입니다.
    - JWT Signature는 암호화되어 있기 때문에, 외부에서 위의 사진처럼 Decoding을 진행해도
        
        실제 서명부가 나오지 않고, 암호화의 구조만 나타나게 됩니다.
        
    - 암호화 구조를 살펴보면, 앞서 JWT 정의에 대해서 말할 때 언급되었던
        
        **base64UrlEncode**를 사용하여 header와 payload를 암호화한 것을 볼 수 있습니다.
        
    - 그 다음은 **your-256-bit-secret로, 서버가 가지고 있는 개인키를 통해 암호화되어 있습니다.**
        
        이렇게 서버가 가지고 있는 개인키를 통해 암호화되어 있기 때문에
        
        외부에서 Signature를 복호화할 수 없는 것입니다.
        

# 3. JWT - AccessToken & Refresh Token

- AccessToken : JWT인증시 사용되는 토큰
- Refresh Token: AccessToken을 오래 사용하다보면 여러 곳에서 탈취가 일어날수 있어서 정보 누출의 우려가 있어서 유효기간을 짧게 해서 유효기간이 지나면 Refresh Token으로 AccessToken을 재발급 받는것을 의미한다.
- 자세한 정리
    1. AccessToken
    처음 로그인 요청 시 서버에서 실제 유저의 정보가 담긴 AccessToken을 발행합니다.
    클라이언트는 이 AccessToken을 저장한 후, 요청마다 AccessToken을 보내서
    해당 AccessToken을 서버에서 검증 후 유효하면 요청에 맞는 응답을 진행합니다.
    2. RefreshToken
    처음 로그인 요청 시 서버에서 AccessToken 재발급 용도인 RefreshToken을 발행합니다.
    이때, 클라이언트는 RefreshToken을 저장하지 않고 RefreshToken은 보통 서버 DB에 저장됩니다.
    RefreshToken이 유효하면, AccessToken의 재발급을 진행합니다.
-

package br.com.trainee.MoovieNight.util;

import br.com.trainee.MoovieNight.domain.entity.ContaEntity;
import br.com.trainee.MoovieNight.domain.enuns.TipoConta;
import br.com.trainee.MoovieNight.util.dto.JwtDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.LocalDate;
import java.util.Date;

public class JwtUtil {
    static Key chaveSecreta = JwtConfig.getChaveSecreta();

    public static String generateToken(ContaEntity conta) {
        return Jwts.builder()
                .setSubject(conta.getEmail())
                .claim("id", conta.getIdConta())
                .claim("idCartao", conta.getIdCartao())
                .claim("email", conta.getEmail())
                .claim("dataNascimento", conta.getDataNascimento().toString())
                .claim("tipoConta", conta.getTipoConta().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, chaveSecreta)
                .compact();
    }

    public static JwtDto decodeToken(String token) {
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(chaveSecreta)
                .build()
                .parseClaimsJws(token);
        Claims claims = jws.getBody();

        JwtDto jwtDto = new JwtDto();
        jwtDto.setId(claims.get("id", Integer.class));
        jwtDto.setEmail(claims.get("email", String.class));
        jwtDto.setDataNascimento(claims.get("dataNascimento", String.class));
        jwtDto.setTipoConta(TipoConta.valueOf(claims.get("tipoConta", String.class)));
        jwtDto.setIdCartao(claims.get("idCartao", String.class));

        return jwtDto;
    }
}

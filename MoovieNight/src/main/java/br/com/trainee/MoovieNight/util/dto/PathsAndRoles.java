package br.com.trainee.MoovieNight.util.dto;

import br.com.trainee.MoovieNight.domain.enuns.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class PathsAndRoles {
    private String path;
    private List<TipoConta> roles;
}

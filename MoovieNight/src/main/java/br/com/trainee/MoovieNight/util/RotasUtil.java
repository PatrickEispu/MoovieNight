package br.com.trainee.MoovieNight.util;

import br.com.trainee.MoovieNight.domain.enuns.TipoConta;
import br.com.trainee.MoovieNight.util.dto.PathsAndRoles;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RotasUtil {
    public static Map<String, List<TipoConta>> getRotas(ResourceLoader resourceLoader) {
        Map<String, List<TipoConta>> pathsAndRoles = new HashMap<>();
        Resource resource = resourceLoader.getResource("classpath:rotas.json");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<PathRoleMapping> rolePathList = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<PathRoleMapping>>() {
            });
            for (PathRoleMapping rolePath : rolePathList) {
                pathsAndRoles.put(rolePath.getPath(), rolePath.getRoles());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathsAndRoles;
    }

    private static class PathRoleMapping {
        private String path;
        private List<TipoConta> roles;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<TipoConta> getRoles() {
            return roles;
        }

        public void setRoles(List<TipoConta> roles) {
            this.roles = roles;
        }
    }
}

package io.example.app.backend.integration.filter;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformSearch {
    public <T> List<T> searchEntities(JpaSpecificationExecutor<T> repository, String search) {
        Specification<T> spec = Specification.where(null);
        if (search != null && !search.isEmpty()) {
            String[] criteria = search.split(",");
            for (String criterion : criteria) {
                String[] parts = criterion.split(":");
                if (parts.length == 2) {
                    String propertyName = parts[0];
                    String value = parts[1];
                    spec = spec.and((root, query, builder) -> {
                        if (propertyName.contains(">")) {
                            String[] propParts = propertyName.split(">");
                            return builder.greaterThan(root.get(propParts[0]), value);
                        } else if (propertyName.contains("<")) {
                            String[] propParts = propertyName.split("<");
                            return builder.lessThan(root.get(propParts[0]), value);
                        } else {
                            return builder.equal(root.get(propertyName), value);
                        }
                    });
                }
            }
        }
        return repository.findAll(spec);
    }
}

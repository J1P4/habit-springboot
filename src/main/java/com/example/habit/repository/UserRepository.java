package com.example.habit.repository;

import com.example.habit.domain.User;
import com.example.habit.type.EProvider;
import com.example.habit.type.ERole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.socialId = :id AND u.eProvider = :provider")
    Optional<UserSecurityForm> findBySocialIdAndEProvider(@Param("id") String id, @Param("provider") EProvider provider);

    @EntityGraph(attributePaths = {"userEssentialNutrients.essentialNutrients"})
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findByIdWithUserEssentialNutrients(@Param("id") Long Id);

    interface UserSecurityForm {

        static UserSecurityForm invoke(User user) {


            return new UserSecurityForm() {
                @Override
                public Long getId() {
                    return user.getId();
                }

                @Override
                public ERole getRole() {
                    return user.getRole();
                }
            };
        }

        Long getId();

        ERole getRole();
    }
}

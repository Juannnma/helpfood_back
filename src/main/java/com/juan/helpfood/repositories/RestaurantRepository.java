package com.juan.helpfood.repositories;

import com.juan.helpfood.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN r.menuList m JOIN m.dishes d JOIN d.tags t " +
            "WHERE t.id IN :tagIds GROUP BY r.id " +
            "HAVING COUNT(DISTINCT t.id) = :tagCount")
    List<Restaurant> findRestaurantsByDishTags(@Param("tagIds") List<Integer> tagsIds, @Param("tagCount") Integer tagCount);
}

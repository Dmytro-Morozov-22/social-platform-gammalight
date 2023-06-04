package com.ua.vinn.GammaLight.repositories.test_repositories;

import com.ua.vinn.GammaLight.models.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface LikeRepository<T, ID> extends JpaRepository<T, ID> {

    @Query("select u from #{#entityName} u where u.user.id = ?1")
    Optional<T> findByUserId(Long userId);

//SELECT count(post_id) FROM like_post WHERE post_id = 40;
//@Query("update #{#entityName} set status = :status")
//UPPER(firstName) LIKE %?#{[0].toUpperCase()}%"

//    String subs = b.substring(5) + "_id";
//    @Query("select count(u) from #{#entityName} u where LIKE %#{#entityName}% = ?1")
//    @Query(value="select count(*) from #{#entityName} a where #{a.substring(5) + _id} = ?1", nativeQuery=true)

//    @Query("select count(u) from #{#entityName} u where u.user.comment.id = ?1")
//    long countByEntityId(Long entityId);
}

//
/*
    @NoRepositoryBean
    public interface DemoRepository<T extends BaseEntity> extends JpaRepository<T, String> {
        @Query("select u from #{#entityName} u where u.columnVal = ?1")
        List<T> findByColumnVal(String columnVal);
    }
 */
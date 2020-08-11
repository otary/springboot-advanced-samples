package cn.chenzw.springboot.elasticsearch.data.repository.es;

import cn.chenzw.springboot.elasticsearch.data.domain.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenzw
 */
@Repository
public interface SysUserRepository extends ElasticsearchRepository<SysUser, Long> {

    /**
     * AND 语句查询
     */
    List<SysUser> findByUsernameAndState(String username, Byte state2);


    /**
     * OR 语句查询
     *
     * @param username
     * @param name
     * @return
     */
    List<SysUser> findByUsernameOrName(String username, String name);


    /**
     * 分页查询
     *
     * @param username
     * @param pageable
     * @return
     */
    Page<SysUser> findByUsername(String username, Pageable pageable);

    /**
     * 模糊查询
     *
     * @param username
     * @param pageable
     * @return
     */
    Page<SysUser> findByUsernameLike(String username, Pageable pageable);

}

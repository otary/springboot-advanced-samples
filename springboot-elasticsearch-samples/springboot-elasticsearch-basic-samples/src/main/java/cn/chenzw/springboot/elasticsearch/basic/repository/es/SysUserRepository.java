package cn.chenzw.springboot.elasticsearch.basic.repository.es;

import cn.chenzw.springboot.elasticsearch.basic.domain.entity.SysUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chenzw
 */
@Repository
public interface SysUserRepository extends ElasticsearchRepository<SysUser, Long> {

}

package cn.chenzw.springboot.elasticsearch.data.service;

import cn.chenzw.springboot.elasticsearch.data.domain.entity.SysUser;
import cn.chenzw.springboot.elasticsearch.data.repository.es.SysUserRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysUserService {

    @Autowired
    SysUserRepository sysUserRepository;

    public Long save(SysUser sysUser) {
        SysUser user = sysUserRepository.save(sysUser);
        return user.getId();
    }

    public List<SysUser> search(Integer offset,
                                Integer pageSize,
                                String searchContent) {

        // 分页
        Pageable pageable = PageRequest.of(offset, pageSize);


        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("username", searchContent)),
                ScoreFunctionBuilders.weightFactorFunction(1000));

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(functionScoreQueryBuilder).build();

        Page<SysUser> searchPageResults = sysUserRepository.search(searchQuery);
        return searchPageResults.getContent();
    }

    List<SysUser> findByUsernameAndState(String username, Byte state) {
        return sysUserRepository.findByUsernameAndState(username, state);
    }

    List<SysUser> findByUsernameOrName(String username, String name) {
        return sysUserRepository.findByUsernameOrName(username, name);
    }

    Page<SysUser> findByUsername(String username, Pageable pageable) {
        return sysUserRepository.findByUsername(username, pageable);
    }

    Page<SysUser> findByUsernameLike(String username, Pageable pageable) {
        return sysUserRepository.findByUsernameLike(username, pageable);
    }

    void deleteSysUserByUsername(String username) {
        sysUserRepository.deleteSysUserByUsername(username);
    }

}

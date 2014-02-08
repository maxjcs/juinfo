package com.hlx.juinfo.dal.biz.dal.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.hlx.juinfo.dal.biz.dal.dao.MovieDAO;
import com.hlx.juinfo.dal.biz.dal.dataobject.MovieDO;

/**
 * 数据访问对象实现类
 * @since 2013-01-27
 */
public class MovieDAOImpl extends SqlMapClientDaoSupport implements MovieDAO {

    /**
     * 插入数据
     * @param movieDO
     * @return 插入数据的主键
     */
    public Integer insertMovieDO(MovieDO movieDO) throws DataAccessException {
        Object id = getSqlMapClientTemplate().insert("Movie.insert", movieDO);
        return (Integer) id;
    }

    /**
     * 统计记录数
     * @param movieDO
     * @return 查出的记录数
     */
    public Integer countMovieDOByExample(MovieDO movieDO) throws DataAccessException {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("Movie.countByDOExample", movieDO);
        return count;
    }

    /**
     * 更新记录
     * @param movieDO
     * @return 受影响的行数
     */
    public Integer updateMovieDO(MovieDO movieDO) throws DataAccessException {
        int result = getSqlMapClientTemplate().update("Movie.update", movieDO);
        return result;
    }

    /**
     * 获取对象列表
     * @param movieDO
     * @return 对象列表
     */
    @SuppressWarnings("unchecked")
    public List<MovieDO> findListByExample(MovieDO movieDO) throws DataAccessException {
        List<MovieDO> list = getSqlMapClientTemplate().queryForList("Movie.findListByDO", movieDO);
        return list;
    }

    /**
     * 根据主键获取movieDO
     * @param id
     * @return movieDO
     */
    public MovieDO findMovieDOByPrimaryKey(Integer id) throws DataAccessException {
        MovieDO movieDO = (MovieDO) getSqlMapClientTemplate().queryForObject("Movie.findByPrimaryKey", id);
        return movieDO;
    }

    /**
     * 删除记录
     * @param id
     * @return 受影响的行数
     */
    public Integer deleteMovieDOByPrimaryKey(Integer id) throws DataAccessException {
        Integer rows = (Integer) getSqlMapClientTemplate().delete("Movie.deleteByPrimaryKey", id);
        return rows;
    }
    
    @Override
    public List<MovieDO> queryByParam(Map paramMap) throws DataAccessException {
        List<MovieDO> list = getSqlMapClientTemplate().queryForList("Movie.findListByParam", paramMap);
        return list;
    }

    @Override
    public Integer countByParam(Map paramMap) throws DataAccessException {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("Movie.countByParam", paramMap);
        return count;
    }

}
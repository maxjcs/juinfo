package com.hlx.juinfo.dal.biz.dal.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.hlx.juinfo.dal.biz.dal.dao.MovieDAO;
import com.hlx.juinfo.dal.biz.dal.dataobject.MovieDO;

/**
 * ���ݷ��ʶ���ʵ����
 * @since 2013-01-27
 */
public class MovieDAOImpl extends SqlMapClientDaoSupport implements MovieDAO {

    /**
     * ��������
     * @param movieDO
     * @return �������ݵ�����
     */
    public Integer insertMovieDO(MovieDO movieDO) throws DataAccessException {
        Object id = getSqlMapClientTemplate().insert("Movie.insert", movieDO);
        return (Integer) id;
    }

    /**
     * ͳ�Ƽ�¼��
     * @param movieDO
     * @return ����ļ�¼��
     */
    public Integer countMovieDOByExample(MovieDO movieDO) throws DataAccessException {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("Movie.countByDOExample", movieDO);
        return count;
    }

    /**
     * ���¼�¼
     * @param movieDO
     * @return ��Ӱ�������
     */
    public Integer updateMovieDO(MovieDO movieDO) throws DataAccessException {
        int result = getSqlMapClientTemplate().update("Movie.update", movieDO);
        return result;
    }

    /**
     * ��ȡ�����б�
     * @param movieDO
     * @return �����б�
     */
    @SuppressWarnings("unchecked")
    public List<MovieDO> findListByExample(MovieDO movieDO) throws DataAccessException {
        List<MovieDO> list = getSqlMapClientTemplate().queryForList("Movie.findListByDO", movieDO);
        return list;
    }

    /**
     * ����������ȡmovieDO
     * @param id
     * @return movieDO
     */
    public MovieDO findMovieDOByPrimaryKey(Integer id) throws DataAccessException {
        MovieDO movieDO = (MovieDO) getSqlMapClientTemplate().queryForObject("Movie.findByPrimaryKey", id);
        return movieDO;
    }

    /**
     * ɾ����¼
     * @param id
     * @return ��Ӱ�������
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
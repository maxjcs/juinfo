package com.hlx.juinfo.dal.biz.dal.dao.impl;

import com.hlx.juinfo.dal.biz.dal.dao.MoviePinDAO;
import com.hlx.juinfo.dal.biz.dal.dataobject.MoviePinDO;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * ���ݷ��ʶ���ʵ����
 * @since 2013-01-17
 */
public class MoviePinDAOImpl extends SqlMapClientDaoSupport implements MoviePinDAO {

    /**
     * ��������
     * @param moviePinDO
     * @return �������ݵ�����
     */
    public Integer insertMoviePinDO(MoviePinDO moviePinDO) throws DataAccessException {
        Object id = getSqlMapClientTemplate().insert("MoviePin.insert", moviePinDO);
        return (Integer) id;
    }

    /**
     * ͳ�Ƽ�¼��
     * @param moviePinDO
     * @return ����ļ�¼��
     */
    public Integer countMoviePinDOByExample(MoviePinDO moviePinDO) throws DataAccessException {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("MoviePin.countByDOExample", moviePinDO);
        return count;
    }

    /**
     * ���¼�¼
     * @param moviePinDO
     * @return ��Ӱ�������
     */
    public Integer updateMoviePinDO(MoviePinDO moviePinDO) throws DataAccessException {
        int result = getSqlMapClientTemplate().update("MoviePin.update", moviePinDO);
        return result;
    }

    /**
     * ��ȡ�����б�
     * @param moviePinDO
     * @return �����б�
     */
    @SuppressWarnings("unchecked")
    public List<MoviePinDO> findListByExample(MoviePinDO moviePinDO) throws DataAccessException {
        List<MoviePinDO> list = getSqlMapClientTemplate().queryForList("MoviePin.findListByDO", moviePinDO);
        return list;
    }

    /**
     * ����������ȡmoviePinDO
     * @param id
     * @return moviePinDO
     */
    public MoviePinDO findMoviePinDOByPrimaryKey(Integer id) throws DataAccessException {
        MoviePinDO moviePinDO = (MoviePinDO) getSqlMapClientTemplate().queryForObject("MoviePin.findByPrimaryKey", id);
        return moviePinDO;
    }

    /**
     * ɾ����¼
     * @param id
     * @return ��Ӱ�������
     */
    public Integer deleteMoviePinDOByPrimaryKey(Integer id) throws DataAccessException {
        Integer rows = (Integer) getSqlMapClientTemplate().delete("MoviePin.deleteByPrimaryKey", id);
        return rows;
    }

}
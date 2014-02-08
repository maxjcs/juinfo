package com.hlx.juinfo.dal.biz.dal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.hlx.juinfo.dal.biz.dal.dataobject.MovieDO;

/**
 * ���ݷ��ʶ���ӿ�
 * @since 2013-01-27
 */
public interface MovieDAO {

    /**
     * ��������
     * @param movieDO
     * @return �������ݵ�����
     */
    public Integer insertMovieDO(MovieDO movieDO) throws DataAccessException;

    /**
     * ͳ�Ƽ�¼��
     * @param movieDO
     * @return ����ļ�¼��
     */
    public Integer countMovieDOByExample(MovieDO movieDO) throws DataAccessException;

    /**
     * ���¼�¼
     * @param movieDO
     * @return ��Ӱ�������
     */
    public Integer updateMovieDO(MovieDO movieDO) throws DataAccessException;

    /**
     * ��ȡ�����б�
     * @param movieDO
     * @return �����б�
     */
    public List<MovieDO> findListByExample(MovieDO movieDO) throws DataAccessException;

    /**
     * ����������ȡmovieDO
     * @param id
     * @return movieDO
     */
    public MovieDO findMovieDOByPrimaryKey(Integer id) throws DataAccessException;

    /**
     * ɾ����¼
     * @param id
     * @return ��Ӱ�������
     */
    public Integer deleteMovieDOByPrimaryKey(Integer id) throws DataAccessException;
    
    /**
     * ��ѯ��¼
     * @param paramMap
     * @return
     * @throws DataAccessException
     */
    public List<MovieDO> queryByParam( Map paramMap) throws DataAccessException;
    
    /**
     * count��ѯ��¼
     * @param paramMap
     * @return
     * @throws DataAccessException
     */
    public Integer countByParam( Map paramMap) throws DataAccessException;

}
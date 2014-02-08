package com.hlx.juinfo.dal.biz.dal.dao;

import com.hlx.juinfo.dal.biz.dal.dataobject.MoviePinDO;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * ���ݷ��ʶ���ӿ�
 * @since 2013-01-17
 */
public interface MoviePinDAO {

    /**
     * ��������
     * @param moviePinDO
     * @return �������ݵ�����
     */
    public Integer insertMoviePinDO(MoviePinDO moviePinDO) throws DataAccessException;

    /**
     * ͳ�Ƽ�¼��
     * @param moviePinDO
     * @return ����ļ�¼��
     */
    public Integer countMoviePinDOByExample(MoviePinDO moviePinDO) throws DataAccessException;

    /**
     * ���¼�¼
     * @param moviePinDO
     * @return ��Ӱ�������
     */
    public Integer updateMoviePinDO(MoviePinDO moviePinDO) throws DataAccessException;

    /**
     * ��ȡ�����б�
     * @param moviePinDO
     * @return �����б�
     */
    public List<MoviePinDO> findListByExample(MoviePinDO moviePinDO) throws DataAccessException;

    /**
     * ����������ȡmoviePinDO
     * @param id
     * @return moviePinDO
     */
    public MoviePinDO findMoviePinDOByPrimaryKey(Integer id) throws DataAccessException;

    /**
     * ɾ����¼
     * @param id
     * @return ��Ӱ�������
     */
    public Integer deleteMoviePinDOByPrimaryKey(Integer id) throws DataAccessException;

}
package com.crm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	//提供构造方法传入具体class
	private Class clazz;
	public BaseDaoImpl() {
		//反射获得class
		this.clazz=this.getClass();
		//获得父类的参数化类型==>例如 BaseDaoImpl<LinkMan>
		Type type=clazz.getGenericSuperclass();
		//System.out.println(type);
		//将type强转成参数化的类型
		ParameterizedType parameterizedType=(ParameterizedType) type;
		//返回表示此类型实际类型参数
		Type[] types=parameterizedType.getActualTypeArguments();
		this.clazz=(Class) types[0];//泛型参数 Customer ` LinkMan 等
	}
	
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
		
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
		
	}

	@Override
	public T findById(Serializable id) {
		
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getName());
	}

	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list=(List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria)
;		return list.size()>0?list.get(0).intValue():0;
	}

	@Override
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		List<T> list=(List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
		return list;
	}

}

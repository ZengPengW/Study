package com.zp.oauth2.server.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
public class TbRole implements Serializable {
 
    private Long id;

    /**
     * 父角色
     */
   
    private Long parentId;

    /**
     * 角色名称
     */
  
    private String name;

    /**
     * 角色英文名称
     */
  
    private String enname;

    /**
     * 备注
     */
   
    private String description;

    
    private Date created;

   
    private Date updated;

}

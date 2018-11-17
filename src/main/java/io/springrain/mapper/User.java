package io.springrain.mapper;

import io.aprilfish.mybatis.annotations.PK;
import io.aprilfish.mybatis.annotations.Table;
import org.apache.commons.lang.time.DateUtils;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;

@Table(name = "user")
public class User {

    @PK(auto = true)
    private Long id;

    private String name;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setCreateDate(String createDate) throws ParseException {
        this.createDate = DateUtils.parseDate(createDate, new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"});
    }

    @Override
    public String toString() {
        return MessageFormat.format("'{'\"id\":{0},\"name\":\"{1}\"'}'", this.getId(), this.getName());
    }

}

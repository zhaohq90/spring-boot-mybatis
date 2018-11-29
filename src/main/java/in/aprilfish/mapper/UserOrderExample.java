package in.aprilfish.mapper;

import in.aprilfish.mybatis.query.Example;

public class UserOrderExample extends Example {

    private Criteria criteria=createCriteria();

    public FieldPath<UserOrderExample> id=new FieldPath<>(this,criteria,"id","id");

    public FieldPath<UserOrderExample> uid=new FieldPath<>(this,criteria,"uid","uid");

    public FieldPath<UserOrderExample> name=new FieldPath<>(this,criteria,"name","name");

    @Override
    public Criteria or(){
        criteria=super.or();

        resetCriteria(criteria);

        return criteria;
    }

    private void resetCriteria(Criteria criteria){
        id.setCriteria(criteria);
        uid.setCriteria(criteria);
        name.setCriteria(criteria);
    }

}
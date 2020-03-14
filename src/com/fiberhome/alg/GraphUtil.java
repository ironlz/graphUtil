package com.fiberhome.alg;

import com.fiberhome.alg.model.Direction;
import com.fiberhome.alg.model.Relationship;

import java.util.*;

public class GraphUtil {

    /**
     * 用于去除双向边的节点封装类，不考虑关系类型
     */
    private static class NodeHolder{
        private String mKey;
        private String mValue;
        private String uniqueValue;
//        private String relType;
//
        public NodeHolder(String mKey, String mValue, String uniqueValue, String relType) {
            this.mKey = mKey;
            this.mValue = mValue;
            this.uniqueValue = uniqueValue;
//            this.relType = relType;
        }

        public String getmKey() {
            return mKey;
        }

        public String getmValue() {
            return mValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof NodeHolder)) return false;
            NodeHolder that = (NodeHolder) o;
            return Objects.equals(getmKey(), that.getmKey()) &&
                    Objects.equals(getmValue(), that.getmValue()) &&
                    Objects.equals(uniqueValue, that.uniqueValue)/* &&
                    Objects.equals(relType, that.relType)*/;
        }

        @Override
        public int hashCode() {
            return Objects.hash(getmKey(), getmValue(), uniqueValue/*, relType*/);
        }
    }

    /**
     * 相同两个节点之间、同一uniqueValue下，将双向边随机去除一条
     * @param rels 要处理的双向边集合
     * @return 处理结果
     */
    public static Set<Relationship> tailorBothRelation(Set<Relationship> rels){
        Set<Relationship> result = new HashSet<>();
        Map<NodeHolder, Set<NodeHolder>> tmp = new HashMap<>();
        for (Relationship rel : rels) {
            NodeHolder startNode = new NodeHolder(rel.getStartMKey(), rel.getStartMValue(), rel.getUniqueValue(), rel.getRelType());
            NodeHolder endNode = new NodeHolder(rel.getEndMKey(), rel.getEndMValue(), rel.getUniqueValue(), rel.getRelType());
            if ((tmp.containsKey(startNode) && tmp.get(startNode).contains(endNode)) ||
                    (tmp.containsKey(endNode) && tmp.get(endNode).contains(startNode))){
                continue;
            }
            else{
                Set<NodeHolder> adjacency = tmp.get(startNode) != null ? tmp.get(startNode) : tmp.get(endNode);
                if (adjacency == null){
                    adjacency = new HashSet<>();
                    tmp.put(startNode, adjacency);
                }
                adjacency.add(endNode);
                result.add(rel);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String personType = "PERSON";
        String carType = "CAR";
        String trainType = "TRAIN";
        String planeType = "PLANE";
        String person2car = "PERSON_TO_CAR";
        String person2train = "PERSON_TO_TRAIN";
        String person2plane = "PERSON_TO_PLANE";
        String date1 = "date1";
        String date2 = "date2";
        String person1 = "p1";
        String person2 = "p2";
        String person3 = "p3";

        Set<Relationship> rels = new HashSet<>();
        rels.add(new Relationship(1,6, personType, carType, person1, carType, Direction.INCOMING, date1, person2car));
        rels.add(new Relationship(1,6, personType, carType, person1, carType, Direction.OUTGOING, date1, person2car));
        rels.add(new Relationship(6,1, carType, personType, carType, person1, Direction.INCOMING, date1, person2car));
        rels.add(new Relationship(6,1, carType, personType, carType, person1, Direction.OUTGOING, date1, person2car));

        rels.add(new Relationship(1,5, personType, trainType, person1, trainType, Direction.INCOMING, date2, person2train));
        rels.add(new Relationship(1,5, personType, trainType, person1, trainType, Direction.OUTGOING, date2, person2train));
        rels.add(new Relationship(5,1, trainType, personType, trainType, person1, Direction.INCOMING, date2, person2train));
        rels.add(new Relationship(5,1, trainType, personType, trainType, person1, Direction.OUTGOING, date2, person2train));

        rels.add(new Relationship(1,4, personType, planeType, person1, planeType, Direction.INCOMING, date1, person2plane));
        rels.add(new Relationship(1,4, personType, planeType, person1, planeType, Direction.OUTGOING, date1, person2plane));
        rels.add(new Relationship(4,1, planeType, personType, planeType, person1, Direction.INCOMING, date1, person2plane));
        rels.add(new Relationship(4,1, planeType, personType, planeType, person1, Direction.OUTGOING, date1, person2plane));

        rels.add(new Relationship(2,6, personType, carType, person3, carType, Direction.INCOMING, date1, person2car));
        rels.add(new Relationship(2,6, personType, carType, person3, carType, Direction.INCOMING, date2, person2car));
        rels.add(new Relationship(2,6, personType, carType, person3, carType, Direction.OUTGOING, date1, person2car));
        rels.add(new Relationship(2,6, personType, carType, person3, carType, Direction.OUTGOING, date2, person2car));

        rels.add(new Relationship(6,2, carType, personType, carType, person3, Direction.INCOMING, date1, person2car));
        rels.add(new Relationship(6,2, carType, personType, carType, person3, Direction.INCOMING, date2, person2car));
        rels.add(new Relationship(6,2, carType, personType, carType, person3, Direction.OUTGOING, date1, person2car));
        rels.add(new Relationship(6,2, carType, personType, carType, person3, Direction.OUTGOING, date2, person2car));



        rels.add(new Relationship(3,6, personType, carType, person2, carType, Direction.INCOMING, date1, person2car));
        rels.add(new Relationship(3,6, personType, carType, person2, carType, Direction.OUTGOING, date1, person2car));
        rels.add(new Relationship(6,3, carType, personType, carType, person2, Direction.INCOMING, date1, person2car));
        rels.add(new Relationship(6,3, carType, personType, carType, person2, Direction.OUTGOING, date1, person2car));

        rels.add(new Relationship(3,5, personType, trainType, person2, trainType, Direction.INCOMING, date1, person2train));
        rels.add(new Relationship(3,5, personType, trainType, person2, trainType, Direction.OUTGOING, date1, person2train));
        rels.add(new Relationship(5,3, trainType, personType, trainType, person2, Direction.INCOMING, date1, person2train));
        rels.add(new Relationship(5,3, trainType, personType, trainType, person2, Direction.OUTGOING, date1, person2train));

        rels.add(new Relationship(3,4, personType, planeType, person2, planeType, Direction.INCOMING, date1, person2plane));
        rels.add(new Relationship(3,4, personType, planeType, person2, planeType, Direction.OUTGOING, date1, person2plane));
        rels.add(new Relationship(4,3, planeType, personType, planeType, person2, Direction.INCOMING, date1, person2plane));
        rels.add(new Relationship(4,3, planeType, personType, planeType, person2, Direction.OUTGOING, date1, person2plane));

        Set<Relationship> relationships = tailorBothRelation(rels);
        rels = null;
        for (Relationship relationship : relationships) {
            System.out.println(relationship);
        }

    }
}

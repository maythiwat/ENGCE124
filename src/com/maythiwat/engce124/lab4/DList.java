package com.maythiwat.engce124.lab4;

public class DList {
    private DNode head, tail;
    private int count;

    public void append(String data) {
        DNode newNode = new DNode(data);
        if (count == 0) {
            head = newNode;
        } else {
            tail.rLink = newNode;
            newNode.lLink = tail;
        }
        tail = newNode;
        count++;
    }

    public void prepend(String data) {
        DNode newNode = new DNode(data);
        if (count == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.rLink = head;
            head.lLink = newNode;
            head = newNode;
        }
        count++;
    }

    public void insertAfter(int index, String data) {
        DNode position = getNodeAt(index);
        if (position != null) {
            if (position == tail) {
                append(data);
            } else {
                DNode newNode = new DNode(data);
                newNode.rLink = position.rLink;
                newNode.lLink = position;
                position.rLink.lLink = newNode;
                position.rLink = newNode;
                count++;
            }
        }
    }

    public void insertBefore(int index, String data) {
        DNode position = getNodeAt(index);
        if (position != null) {
            if (position == head) {
                prepend(data);
            } else {
                DNode newNode = new DNode(data);
                newNode.rLink = position;
                newNode.lLink = position.lLink;
                position.lLink.rLink = newNode;
                position.lLink = newNode;
                count++;
            }
        }
    }

    public DNode search(String target) {
        DNode travel = head;
        while (travel != null && target.equals(travel.info)) {
            travel = travel.rLink;
        }
        return travel;
    }

    public DNode getNodeAt(int index) {
        if (index >= 0 && index < count) {
            DNode travel = head;
            for (int i = 0; i < index; i++) {
                travel = travel.rLink;
            }
            return travel;
        }
        return null;
    }

    public void deleteAt(int index) {
        if (index >= 0 && index < count) {
            if (index == 0) {
                head = head.rLink;
                if (head != null) {
                    head.lLink = null;
                } else {
                    tail = null;
                }
            } else if (index == count - 1) {
                tail = tail.lLink;
                if (tail != null) {
                    tail.rLink = null;
                } else {
                    head = null;
                }
            } else {
                DNode position = getNodeAt(index);
                position.lLink.rLink = position.rLink;
                position.rLink.lLink = position.lLink;
            }
            count--;
        }
    }

    public void showAll() {
        DNode travel = head;
        while (travel != null) {
            System.out.println(travel.info);
            travel = travel.rLink;
        }
        System.out.println("----------------");
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }
}

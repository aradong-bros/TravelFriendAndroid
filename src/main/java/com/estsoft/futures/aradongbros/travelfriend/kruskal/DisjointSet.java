package com.estsoft.futures.aradongbros.travelfriend.kruskal;
/**
 * Created by gangGongUi on 2016. 8. 3..
 */
public class DisjointSet
{
    private final int MAX = 10001;
    private int[] p = new int[MAX];
    private boolean[] hasSet = new boolean[MAX];

    public DisjointSet()
    {
        for(int i = 0; i < p.length; i++)
            p[i] = i;
    }
    
    public boolean checkCycle(int v1, int v2)
    {
        // 둘다 집합에 있는 경우
        if(hasSet[v1] && hasSet[v2])
        {
            // 같은 집합에 각각 속해 있으면 사이클이 있다.
            if(find(v1) == find(v2))
            {
                return true;
            }
            // 서로 다른 집합일 경우 합집합으로!
            else
            {
                union(v1, v2);
            }
        }
        // v1 만 집합에 속해있는 경우
        else if(hasSet[v1] && !hasSet[v2])
        {
            union(v1,v2);
            hasSet[v2] = true;
        }
        // v2 만 집합에 속해있는 경우
        else if(!hasSet[v1] && hasSet[v2])
        {
            union(v2,v1);
            hasSet[v1] = true;
        }
        // 어디에도 집합이 없는 경우
        else
        {
            hasSet[v1] = hasSet[v2] = true;
            union(v1, v2);
        }

        return false;
    }

    // 합치는 거
    private void union(int v1, int v2)
    {
    	//v의 대표 집단을 찾는다.
        v1 = find(v1);
        v2 = find(v2);
        p[v2] = v1;
    }
    
    // 대표집단을 찾는거
    private int find(int v)
    {
        if(v == p[v])
        {
        	return v;        	
        }
        else 
        {
        	return p[v] = find(p[v]);
        }
    }
}
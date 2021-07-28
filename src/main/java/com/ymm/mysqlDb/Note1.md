# MySQL事务
##    ACID
###    A：原子性
###    C：一致性
###    I：隔离性
###    D：持久性
    
##    一致性：undo log
##    持久性：redo log
    
##    隔离性：
###        写操作与写操作：锁实现
###        写操作与读操作：MVCC

# MVCC
##  版本链
版本链是记录每次修改的记录。包含了修改的事务id（creator_trx_id）和指向上一次修改的指针
##  一致性视图
m_ids：当前活动的事务集合
min_trx_id：最小的m_id
max_trx_id：最大的m_id + 1
creator_trx_id：创建事务的事务id
trx_id：当前事务id
##  可见规则
0、trx_id=creator_trx_id。说明是同一事务，可见当前最新版本链的数据。
1、trx_id<min_trx_id或trx_id>=max_trx_id时，可见最新版本链上的数据
2、min_trx_id <=trx_id< max_trx_id，trx_id在m_ids中。不可见，根据版本链回溯。查找符合上述条件的版本数据。
3、min_trx_id <=trx_id< max_trx_id，trx_id不在m_ids中。可见。

package com.ujuit.quant.firmoffer.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

/**
 * redis java版本命令工具 注意：该方法名都与redis提供的方法名相同。
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a> 2016年6月28日
 *         RedisClientTemplate.java
 * @version 1.0
 **/
public interface RedisService {
	/**
	 * 中断连接
	 */
	public void disconnect();

	/**
	 * 设置单个值
	 * 
	 * @param key
	 *            -键
	 * @param value-值
	 * @return
	 */
	public String set(byte[] key, byte value[]);

	/**
	 * 保存数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @date 2017年8月9日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	<T extends Serializable> String setSeria(String key, T value);

	/**
	 * 保存数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @date 2017年8月9日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	<T extends Serializable> T getSeria(String key);

	/**
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key);

	/**
	 * 设置单个值
	 * 
	 * @param key
	 *            -键
	 * @param value-值
	 * @return
	 */
	public String set(String key, String value);

	/**
	 * 根据KEY获取单个值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key);

	/**
	 * 检查给定 key 是否存在。
	 *
	 * 返回值： 若 key 存在，返回 1 true，否则返回 0 false。
	 * 
	 * @param key
	 * @return
	 */
	public Boolean exists(String key);

	/**
	 * 返回 key 所储存的值的类型。
	 * 
	 * none (key不存在) string (字符串) list (列表) set (集合) zset (有序集) hash (哈希表
	 * 
	 * @param key
	 * @return
	 */
	public String type(String key);

	/**
	 * 在某段时间后实现 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。
	 * 
	 * @param key
	 * @param seconds
	 *            秒
	 * @return
	 */
	public Long expire(String key, int seconds);

	/**
	 * 在某个时间点失效 EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置生存时间。 不同在于 EXPIREAT
	 * 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。
	 * 
	 * @param key
	 * @param unixTime
	 *            -时间戳
	 * @return
	 */
	public Long expireAt(String key, long unixTime);

	/**
	 * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
	 * 
	 * @param key
	 * @return
	 */
	public Long ttl(String key);

	/**
	 * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。
	 * 
	 * @param key
	 *            当 key 不存在时，自动生成一个新的字符串值。
	 * @param offset
	 *            offset 参数必须大于或等于 0 ，小于 2^32 (bit 映射被限制在 512 MB 之内)。
	 * @param value
	 * @return
	 */
	public boolean setbit(String key, long offset, boolean value);

	/**
	 * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。
	 * 
	 * @param key
	 * @param offset
	 *            当 offset 比字符串值的长度大，或者 key 不存在时，返回 0 。
	 * @return 字符串值指定偏移量上的位(bit)。
	 */
	public boolean getbit(String key, long offset);

	/**
	 * 用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始。 不存在的 key
	 * 当作空白字符串处理。 SET greeting "hello world" OK redis> SETRANGE greeting 6
	 * "Redis" (integer) 11 redis> GET greeting "hello Redis"
	 * 
	 * @param key
	 * @param offset
	 * @param value
	 * @return
	 */
	public long setrange(String key, long offset, String value);

	/**
	 * 返回 key 中字符串值的子字符串，字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
	 * 负数偏移量表示从字符串最后开始计数， -1 表示最后一个字符， -2 表示倒数第二个，以此类推。
	 * 
	 * @param key
	 * @param startOffset
	 * @param endOffset
	 * @return
	 */
	public String getrange(String key, long startOffset, long endOffset);

	/**
	 * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
	 * 
	 * @param key
	 * @param value
	 * @return 返回 key 的旧值(old value)。
	 */
	public String getSet(String key, String value);

	/**
	 * 将 key 的值设为 value ，当且仅当 key 不存在。
	 * 
	 * @param key
	 * @param value
	 * @return 设置成功，返回 1 设置失败，返回 0 。
	 */
	public Long setnx(String key, String value);

	/**
	 * 将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)。 如果 key 已经存在， SETEX
	 * 命令将覆写旧值
	 * 
	 * @param key
	 * @param seconds
	 * @param value
	 * @return 设置成功时返回 OK 。 当 seconds 参数不合法时，返回一个错误。
	 */
	public String setex(String key, int seconds, String value);

	/**
	 * 将 key 所储存的值减去减量 decrement 。如果 key 不存在，那么 key 的值会先被初始化为 0 ， 然后再执行 DECRBY
	 * 操作。如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。 本操作的值限制在 64 位(bit)有符号数字表示之内。
	 * 关于更多递增(increment) / 递减(decrement)操作的更多信息，请参见 INCR 命令。
	 * 
	 * @param key
	 * @param integer
	 * @return 减去 decrement 之后， key 的值。
	 */
	public Long decrBy(String key, long integer);

	/**
	 * 将 key 中储存的数字值减一。如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作。
	 * 
	 * @param key
	 * @return 执行 DECR 命令之后 key 的值。
	 */
	public Long decr(String key);

	/**
	 * 将 key 中储存的数字值增一。如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
	 * 
	 * @param key
	 * @param integer
	 * @return 执行 INCR 命令之后 key 的值。
	 */
	public Long incrBy(String key, long integer);

	/**
	 * 将 key 中储存的数字值增一
	 * 
	 * @param key
	 * @return 执行 INCR 命令之后 key 的值。
	 */
	public Long incr(String key);

	/**
	 * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。 如果 key 不存在， APPEND
	 * 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
	 * 
	 * @param key
	 * @param value
	 * @return 追加 value 之后， key 中字符串的长度。
	 */
	public Long append(String key, String value);

	/**
	 * 返回key中字符串值的子字符串，字符串的截取范围由start和end两个偏移量决定(包括start和end在内)。
	 * 负数偏移量表示从字符串最后开始计数，-1表示最后一个字符，-2表示倒数第二个，以此类推。
	 * GETRANGE通过保证子字符串的值域(range)不超过实际字符串的值域来处理超出范围的值域请求。
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return 截取得出的子字符串。
	 */
	public String substr(String key, int start, int end);

	/**
	 * 将哈希表 key 中的域 field 的值设为 value 。 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。 如果域
	 * field 已经存在于哈希表中，旧值将被覆盖。
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。 如果哈希表中域 field
	 *         已经存在且旧值已被新值覆盖，返回 0 。
	 */
	public Long hset(String key, String field, String value);

	/**
	 * 返回哈希表 key 中给定域 field 的值。
	 * 
	 * @param key
	 * @param field
	 * @return 给定域的值。 当给定域不存在或是给定 key 不存在时，返回 nil 。
	 */
	public String hget(String key, String field);

	/**
	 * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。 若域 field 已经存在，该操作无效。 如果
	 * key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return 设置成功，返回 1 。 如果给定域已经存在且没有操作被执行，返回 0 。
	 */
	public Long hsetnx(String key, String field, String value);

	/**
	 * 同时将多个 field-value (域-值)对设置到哈希表 key 中。 此命令会覆盖哈希表中已存在的域。 如果 key
	 * 不存在，一个空哈希表被创建并执行 HMSET 操作。
	 * 
	 * @param key
	 * @param hash
	 * @return 如果命令执行成功，返回 OK 。 当 key 不是哈希表(hash)类型时，返回一个错误。
	 */
	public String hmset(String key, Map<String, String> hash);

	/**
	 * 返回哈希表 key 中，一个或多个给定域的值。 如果给定的域不存在于哈希表，那么返回一个 nil 值。 因为不存在的 key
	 * 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。
	 * 
	 * @param key
	 * @param fields
	 * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。
	 */
	public List<String> hmget(String key, String... fields);

	/**
	 * 为哈希表 key 中的域 field 的值加上增量 increment 。
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return 执行 HINCRBY 命令之后，哈希表 key 中域 field 的值。
	 */
	public Long hincrBy(String key, String field, long value);

	/**
	 * 查看哈希表 key 中，给定域 field 是否存在。
	 * 
	 * @param key
	 * @param field
	 * @return 如果哈希表含有给定域，返回 1 。 如果哈希表不含有给定域，或 key 不存在，返回 0 。
	 */
	public Boolean hexists(String key, String field);

	/**
	 * 删除给定的一个 key 。
	 * 
	 * @param key
	 * @return
	 */
	public Long del(String key);

	/**
	 * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
	 * 
	 * @param key
	 * @param fields
	 * @return 被成功移除的域的数量，不包括被忽略的域。
	 */
	public Long hdel(String key, String... fields);

	/**
	 * 返回哈希表 key 中域的数量。
	 * 
	 * @param key
	 * @return 哈希表中域的数量。 当 key 不存在时，返回 0 。
	 */
	public Long hlen(String key);

	/**
	 * 返回哈希表 key 中的所有域。
	 * 
	 * @param key
	 * @return 一个包含哈希表中所有域的表。 当 key 不存在时，返回一个空表。
	 */
	public Set<String> hkeys(String key);

	/**
	 * 返回哈希表 key 中所有域的值。
	 * 
	 * @param key
	 * @return 一个包含哈希表中所有值的表。当 key 不存在时，返回一个空表。
	 */
	public List<String> hvals(String key);

	/**
	 * 返回哈希表 key 中，所有的域和值。 在返回值里，紧跟每个域名(field
	 * name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。
	 * 
	 * @param key
	 * @return 以列表形式返回哈希表的域和域的值。若 key 不存在，返回空列表。
	 */
	public Map<String, String> hgetAll(String key);

	/**
	 * ================list ====== l表示 list或 left, r表示right====================
	 * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。 如果有多个 value 值，那么各个 value
	 * 值按从左到右的顺序依次插入到表尾：比如对一个空列表 mylist 执行 RPUSH mylist a b c ，得出的结果列表为 a b c
	 * ，等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH mylist c 。 如果 key
	 * 不存在，一个空列表会被创建并执行 RPUSH 操作。 当 key 存在但不是列表类型时，返回一个错误。
	 * 
	 * @param key
	 * @param string
	 * @return 执行 RPUSH 操作后，表的长度。
	 */
	public Long rpush(String key, String string);

	/**
	 * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。 如果有多个 value 值，那么各个 value
	 * 值按从左到右的顺序依次插入到表尾：比如对一个空列表 mylist 执行 RPUSH mylist a b c ，得出的结果列表为 a b c
	 * ，等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH mylist c 。 如果 key
	 * 不存在，一个空列表会被创建并执行 RPUSH 操作。 当 key 存在但不是列表类型时，返回一个错误。
	 * 
	 * @param key
	 * @param string
	 * @return 执行 RPUSH 操作后，表的长度。
	 */
	public Long lpush(String key, String string);

	/**
	 * 返回列表 key 的长度。 如果 key 不存在，则 key 被解释为一个空列表，返回 0 . 如果 key 不是列表类型，返回一个错误。
	 * 
	 * @param key
	 * @return 列表 key 的长度。
	 */
	public Long llen(String key);

	/**
	 * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。 下标(index)参数 start 和 stop 都以 0
	 * 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推 <br/>
	 * <font style="color:red"> 注意： 假如你有一个包含一百个元素的列表，对该列表执行 LRANGE list 0 10
	 * ，结果是一个包含11个元素的列表</font>
	 * 
	 * @param key
	 * @param start
	 *            -开始值
	 * @param stop-
	 * @return 一个列表，包含指定区间内的元素。
	 */
	public List<String> lrange(String key, long start, long end);

	/**
	 * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return 命令执行成功时，返回 ok 。
	 */
	public String ltrim(String key, long start, long end);

	/**
	 * 返回列表 key 中，下标为 index 的元素。 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0
	 * 表示列表的第一个元素， 以 1 表示列表的第二个元素，以此类推。 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2
	 * 表示列表的倒数第二个元素，以此类推。 如果 key 不是列表类型，返回一个错误。
	 * 
	 * @param key
	 * @param index
	 * @return 列表中下标为 index 的元素。如果 index 参数的值不在列表的区间范围内(out of range)，返回 nil 。
	 */
	public String lindex(String key, long index);

	/**
	 * 将列表 key 下标为 index 的元素的值设置为 value 。 当 index 参数超出范围，或对一个空列表( key 不存在)进行
	 * LSET 时，返回一个错误。 关于列表下标的更多信息，请参考 LINDEX 命令。
	 * 
	 * @param key
	 * @param index
	 * @param value
	 * @return 操作成功返回 ok ，否则返回错误信息。
	 */
	public String lset(String key, long index, String value);

	/**
	 * 根据参数 count 的值，移除列表中与参数 value 相等的元素。 count 的值可以是以下几种： count > 0 :
	 * 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。 count < 0 : 从表尾开始向表头搜索，移除与 value
	 * 相等的元素，数量为 count 的绝对值。 count = 0 : 移除表中所有与 value 相等的值。
	 * 
	 * @param key
	 * @param count
	 * @param value
	 * @return 被移除元素的数量。 因为不存在的 key 被视作空表(empty list)，所以当 key 不存在时， LREM 命令总是返回
	 *         0 。
	 * 
	 */
	public Long lrem(String key, long count, String value);

	/**
	 * 移除并返回列表 key 的头元素。
	 * 
	 * @param key
	 * @return 列表的头元素。当 key 不存在时，返回 nil
	 */
	public String lpop(String key);

	/**
	 * 移除并返回列表 key 的尾元素。
	 * 
	 * @param key
	 * @return 列表的尾元素。 当 key 不存在时，返回 nil 。
	 * 
	 */
	public String rpop(String key);

	/**
	 * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
	 * 
	 * @param key
	 * @param member
	 * @return 被添加到集合中的新元素的数量，不包括被忽略的元素。
	 */
	public Long sadd(String key, String member);

	/**
	 * 返回集合 key 中的所有成员。不存在的 key 被视为空集合。
	 * 
	 * @param key
	 * @return 集合中的所有成员。
	 */
	public Set<String> smembers(String key);

	/**
	 * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。 当 key 不是集合类型，返回一个错误。
	 * 
	 * @param key
	 * @param member
	 * @return 被成功移除的元素的数量，不包括被忽略的元素。
	 */
	public Long srem(String key, String member);

	/**
	 * 移除并返回集合中的一个随机元素。 如果只想获取一个随机元素，但不想该元素从集合中被移除的话，可以使用 SRANDMEMBER 命令。
	 * 
	 * @param key
	 * @return 被移除的随机元素。当 key 不存在或 key 是空集时，返回 nil 。
	 */
	public String spop(String key);

	/**
	 * 返回集合 key 的基数(集合中元素的数量)。
	 * 
	 * @param key
	 * @return 集合的基数。当 key 不存在时，返回 0
	 */
	public Long scard(String key);

	/**
	 * 判断 member 元素是否集合 key 的成员。
	 * 
	 * @param key
	 * @param member
	 * @return 如果 member 元素是集合的成员，返回 1 。 如果 member 元素不是集合的成员，或 key 不存在，返回 0 。
	 */
	public Boolean sismember(String key, String member);

	/**
	 * 如果命令执行时，只提供了 key 参数，那么返回集合中的一个随机元素。 该操作和 SPOP 相似，但 SPOP 将随机元素从集合中移除并返回，而
	 * SRANDMEMBER 则仅仅返回随机元素，而不对集合进行任何改动。
	 * 
	 * @param key
	 * @return 只提供 key 参数时，返回一个元素；如果集合为空，返回 nil 。 如果提供了 count
	 *         参数，那么返回一个数组；如果集合为空，返回空数组。
	 */
	public String srandmember(String key);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
	 * 
	 * @param key
	 * @param score
	 * @param member
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
	 */
	public Long zadd(String key, double score, String member);

	/**
	 * 返回有序集 key 中，指定区间内的成员。其中成员的位置按 score 值递增(从小到大)来排序。 具有相同 score
	 * 值的成员按字典序(lexicographical order )来排列。 如果你需要成员按 score 值递减(从大到小)来排列，请使用
	 * ZREVRANGE 命令。
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<String> zrange(String key, int start, int end);

	/**
	 * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。当 key 存在但不是有序集类型时，返回一个错误。
	 * 
	 * @param key
	 * @param member
	 * @return 被成功移除的成员的数量，不包括被忽略的成员。
	 */
	public Long zrem(String key, String member);

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment 。 可以通过传递一个负数值 increment ，让
	 * score 减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。
	 * 
	 * @param key
	 * @param score
	 * @param member
	 * @return member 成员的新 score 值，以字符串形式表示。
	 */
	public Double zincrby(String key, double score, String member);

	/**
	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。
	 * 
	 * @param key
	 * @param member
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名。如果 member 不是有序集 key 的成员，返回
	 *         nil 。
	 */
	public Long zrank(String key, String member);

	/**
	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。
	 * 
	 * @param key
	 * @param member
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名。如果 member 不是有序集 key 的成员，返回
	 *         nil 。
	 */
	public Long zrevrank(String key, String member);

	/**
	 * 除了成员按 score 值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<String> zrevrange(String key, int start, int end);

	/**
	 * 按 score 来排列返回有序集key中,指定区间内的成员。 其中成员的位置按score值递增(从小到大)来排序。
	 * 具有相同score值的成员按字典序
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<Tuple> zrangeWithScores(String key, int start, int end);

	/**
	 * 按 score 值递减(从大到小)来排列
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<Tuple> zrevrangeWithScores(String key, int start, int end);

	/**
	 * 返回有序集 key 的基数。
	 * 
	 * @param key
	 * @return
	 */
	public Long zcard(String key);

	/**
	 * 如果 member 元素不是有序集 key 的成员，或 key 不存在，返回 nil 。
	 * 
	 * @param key
	 * @param member
	 * @return 返回有序集 key 中，成员 member 的 score 值。
	 */
	public Double zscore(String key, String member);

	/**
	 * 返回或保存给定列表、集合、有序集合 key 中经过排序的元素。 排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较。
	 * 
	 * @param key
	 * @return
	 */
	List<String> sort(String key);

	/**
	 * 返回或保存给定列表、集合、有序集合 key 中经过排序的元素。 SortingParams sortingParameters = new
	 * SortingParams(); sortingParameters.desc(); //
	 * sortingParameters.alpha();//当数据集中保存的是字符串值时，你可以用 ALPHA //
	 * 修饰符(modifier)进行排序。 sortingParameters.limit(0, 2);// 可用于分页查询
	 * sortingParameters.get("user:*->add");
	 * sortingParameters.by("user:*->name");
	 * 
	 * @param key
	 * @param sortingParameters
	 * @return
	 */
	public List<String> sort(String key, SortingParams sortingParameters);

	/**
	 * score 值在 min 和 max 之间的成员的数量。
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Long zcount(String key, double min, double max);

	/**
	 * 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按
	 *         score 值递增(从小到大)次序排列。
	 */
	public Set<String> zrangeByScore(String key, double min, double max);

	/**
	 * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。有序集成员按 score
	 * 值递减(从大到小)的次序排列
	 * 
	 * @param key
	 * @param max
	 * @param min
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<String> zrevrangeByScore(String key, double max, double min);

	/**
	 * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score
	 * 值递增(从小到大)次序排列。
	 * 
	 * min 和 max 可以是 -inf 和 +inf ，这样一来，你就可以在不知道有序集的最低和最高 score 值的情况下，使用
	 * ZRANGEBYSCORE 这类命令。 默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，你也可以通过给参数前增加 (
	 * 符号来使用可选的开区间 (小于或大于)。
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @param offset
	 * @param count
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count);

	/**
	 * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min )的所有的成员。有序集成员按 score
	 * 值递减(从大到小)的次序排列。
	 * 
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count);

	/**
	 * 根据score进行查询 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min
	 * )的所有的成员。有序集成员按 score 值递减(从大到小)的次序排列。
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max);

	/**
	 * 需要查询
	 * 
	 * @param key
	 * @param max
	 * @param min
	 * @return
	 */
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min);

	/**
	 * @param key
	 * @param min
	 * @param max
	 * @param offset
	 * @param count
	 * @return
	 */
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count);

	/**
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @return
	 */
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count);

	/**
	 * 移除有序集 key 中，指定排名(rank)区间内的所有成员。
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return 被移除成员的数量。
	 */
	public Long zremrangeByRank(String key, int start, int end);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return 被移除成员的数量。
	 */
	public Long zremrangeByScore(String key, double start, double end);

	/**
	 * 将值 value 插入到列表 key 当中，位于值 pivot 之前或之后。 当 pivot 不存在于列表 key 时，不执行任何操作。 当
	 * key 不存在时， key 被视为空列表，不执行任何操作。 如果 key 不是列表类型，返回一个错误。
	 * 
	 * @param key
	 * @param where
	 * @param pivot
	 * @param value
	 * @return 如果命令执行成功，返回插入操作完成之后，列表的长度。 如果没有找到 pivot ，返回 -1 。 如果 key
	 *         不存在或为空列表，返回 0 。
	 */
	public Long linsert(String key, LIST_POSITION where, String pivot, String value);
	/**
	 * @param shardedJedisPipeline
	 * @return
	 */
	// public List<Object> pipelined(ShardedJedisPipeline shardedJedisPipeline);

	/**
	 * 根据KEY获取多个值加前缀
	 * 
	 * @param key
	 * @return
	 */
	public <T> List<T> mget(Class<T> clazz, String... key);

	/**
	 * 根据KEY获取多个值,加前缀
	 * 
	 * @param key
	 * @return
	 */
	public <T> T mgetOne(Class<T> clazz, String key);

	/**
	 * 同时将多个 key-value
	 * 
	 * @param kv
	 * @return 如果命令执行成功，返回 OK 。 当 key 不是哈希表(hash)类型时，返回一个错误。
	 */
	public <T> String mset(Map<String, T> kv);

	/**
	 * 获取对应的key
	 * 
	 * @param pattern
	 * @return
	 * @date 2017年9月21日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	public String[] keys(String pattern);
}

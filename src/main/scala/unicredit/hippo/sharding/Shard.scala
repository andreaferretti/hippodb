package unicredit.hippo.sharding

import com.roundeights.hasher.Implicits._


class Shard(home: String, table: String, partitions: Int, version: String) {
  val indices = 1 to partitions

  def hash(s: String) = s.md5.hex
  def uri(index: Int) = s"$home/$table/$version/shards/$index"
  def index(key: String) = indices maxBy { i => hash(key + i) }

  val uriForKey = (uri _) compose (index _)
}

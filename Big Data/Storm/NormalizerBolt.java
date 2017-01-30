import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

//@Zijiao: to use prepare() method
import backtype.storm.task.TopologyContext;

import java.util.Arrays;
import java.util.List;
import java.util.HashSet;

// prepare() also uses Map
import java.util.Map;

/**
 * A bolt that normalizes the words, by removing common words and making them lower case.
 */
public class NormalizerBolt extends BaseBasicBolt {
  private List<String> commonWords = Arrays.asList("the", "be", "a", "an", "and",
      "of", "to", "in", "am", "is", "are", "at", "not", "that", "have", "i", "it",
      "for", "on", "with", "he", "she", "as", "you", "do", "this", "but", "his",
      "by", "from", "they", "we", "her", "or", "will", "my", "one", "all", "s", "if",
      "any", "our", "may", "your", "these", "d" , " ", "me" , "so" , "what" , "him" );

  private HashSet<String> common = new HashSet<String>();

  @Override
  public void prepare(Map stormConf, TopologyContext context) {
    //@Zijiao: prepare() will be called automatically by Nimbus before running other methods
    for(String word: commonWords) {
      common.add(word);
    }
    return;
  }

  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {

    /*
    ----------------------TODO-----------------------
    Task:
     1. make the words all lower case
     2. remove the common words

    ------------------------------------------------- */

    String word = tuple.getString(0);
    if (!word.isEmpty()) {
      word = word.trim().toLowerCase();
    }
    if (!word.isEmpty()) {
      if (!common.contains(word)) {
        collector.emit(new Values(word));
      }
    }
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer declarer) {

    declarer.declare(new Fields("word"));

  }
}

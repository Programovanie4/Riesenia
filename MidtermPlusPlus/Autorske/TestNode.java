import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TestNode {
    private static LISTTestScoring scoring = null;

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    @Test
    public void test_size() {
        {
            Node<String> rr = new Node<String>("c60a7c37", List.of());
            assertEquals("size/Test_size", 1, rr.size());
        }
        {
            Node<String> rr = new Node<String>("83959767", List.of());
            assertEquals("size/Test_size", 1, rr.size());
        }
        {
            Node<String> rr = new Node<String>("b7865c0f", List.of());
            assertEquals("size/Test_size", 1, rr.size());
        }
        {
            Node<String> rr = new Node<String>("fe2ac3b4", List.of());
            assertEquals("size/Test_size", 1, rr.size());
        }
        {
            Node<String> rr = new Node<String>("1585d00f", List.of());
            assertEquals("size/Test_size", 1, rr.size());
        }
        {
            Node<String> rr = new Node<String>("d839728a", List.of(new Node<String>("f9d6d1b9", List.of())));
            assertEquals("size/Test_size", 2, rr.size());
        }
        {
            Node<String> rr = new Node<String>("ea75aa57", List.of(new Node<String>("dac57f9", List.of()),new Node<String>("74f29d93", List.of()),new Node<String>("d7e83357", List.of())));
            assertEquals("size/Test_size", 4, rr.size());
        }
        {
            Node<String> rr = new Node<String>("23d63d9a", List.of(new Node<String>("d6185c43", List.of())));
            assertEquals("size/Test_size", 2, rr.size());
        }
        {
            Node<String> rr = new Node<String>("6111d898", List.of(new Node<String>("9048a712", List.of())));
            assertEquals("size/Test_size", 2, rr.size());
        }
        {
            Node<String> rr = new Node<String>("57278e77", List.of());
            assertEquals("size/Test_size", 1, rr.size());
        }
        {
            Node<String> rr = new Node<String>("850d62f4", List.of(new Node<String>("c07193de", List.of(new Node<String>("13733ca0", List.of()))),new Node<String>("d13b1edb", List.of(new Node<String>("51e44ada", List.of()),new Node<String>("4079ce8d", List.of()),new Node<String>("b179a5b9", List.of()))),new Node<String>("8b523ed", List.of(new Node<String>("fd41126", List.of()),new Node<String>("16e31a10", List.of())))));
            assertEquals("size/Test_size", 10, rr.size());
        }
        {
            Node<String> rr = new Node<String>("bd7d3b2", List.of(new Node<String>("ade696e1", List.of(new Node<String>("9161c02e", List.of()),new Node<String>("987acb84", List.of()))),new Node<String>("1f00b9d", List.of()),new Node<String>("6937d450", List.of(new Node<String>("7f047656", List.of()),new Node<String>("490bcff2", List.of()),new Node<String>("71e5b30c", List.of())))));
            assertEquals("size/Test_size", 9, rr.size());
        }
        {
            Node<String> rr = new Node<String>("6269bb1f", List.of(new Node<String>("2af044b0", List.of(new Node<String>("2bd19aca", List.of()),new Node<String>("89ff8c26", List.of()),new Node<String>("a4e6574d", List.of()))),new Node<String>("12bd4465", List.of(new Node<String>("9da1de2f", List.of()),new Node<String>("b3a9cc30", List.of()))),new Node<String>("b6286c68", List.of(new Node<String>("acf0bfae", List.of()),new Node<String>("e7dfa074", List.of()),new Node<String>("9050bbb4", List.of()))),new Node<String>("99b615b5", List.of(new Node<String>("73979c55", List.of()),new Node<String>("1d5ddceb", List.of())))));
            assertEquals("size/Test_size", 15, rr.size());
        }
        {
            Node<String> rr = new Node<String>("f18833d2", List.of(new Node<String>("14a39472", List.of(new Node<String>("d2a45ef4", List.of()))),new Node<String>("2f5c8c54", List.of(new Node<String>("549acdb3", List.of()))),new Node<String>("a46def0c", List.of(new Node<String>("51a9a77d", List.of()),new Node<String>("3c001474", List.of()))),new Node<String>("14076285", List.of())));
            assertEquals("size/Test_size", 9, rr.size());
        }
        {
            Node<String> rr = new Node<String>("6bdb5f53", List.of(new Node<String>("d93b7561", List.of()),new Node<String>("e39f2b48", List.of(new Node<String>("fa654a56", List.of()),new Node<String>("d2c995fd", List.of()))),new Node<String>("599ede02", List.of()),new Node<String>("c9467bb5", List.of(new Node<String>("ecc05872", List.of()))),new Node<String>("ef8ce4eb", List.of())));
            assertEquals("size/Test_size", 9, rr.size());
        }
        scoring.updateScore("lang:common_list_test_scoring_name",   17.0D);
    }
    @Test
    public void test_depth() {
        {
            Node<String> rr = new Node<String>("5a342797", List.of());
            assertEquals("depth/Test_depth", 1, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("fb077832", List.of());
            assertEquals("depth/Test_depth", 1, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("d1298f5c", List.of());
            assertEquals("depth/Test_depth", 1, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("a2ae9d92", List.of());
            assertEquals("depth/Test_depth", 1, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("46e8fe74", List.of());
            assertEquals("depth/Test_depth", 1, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("9be94e67", List.of(new Node<String>("7f2c2bd5", List.of())));
            assertEquals("depth/Test_depth", 2, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("b32ed95e", List.of());
            assertEquals("depth/Test_depth", 1, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("ef24036d", List.of(new Node<String>("afb085f0", List.of())));
            assertEquals("depth/Test_depth", 2, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("4321ada4", List.of(new Node<String>("409c9588", List.of())));
            assertEquals("depth/Test_depth", 2, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("1267fc10", List.of(new Node<String>("6c35ddb4", List.of()),new Node<String>("adccbbec", List.of()),new Node<String>("5e036917", List.of())));
            assertEquals("depth/Test_depth", 2, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("57c771fe", List.of(new Node<String>("a411c416", List.of(new Node<String>("9e22b656", List.of())))));
            assertEquals("depth/Test_depth", 3, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("1604b9bf", List.of(new Node<String>("867ca0a6", List.of(new Node<String>("bf58f3f1", List.of()),new Node<String>("af665877", List.of()),new Node<String>("81f22349", List.of()))),new Node<String>("fd55969e", List.of(new Node<String>("97aab26a", List.of())))));
            assertEquals("depth/Test_depth", 3, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("1f4dea3f", List.of());
            assertEquals("depth/Test_depth", 1, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("4e2cd18d", List.of(new Node<String>("e1151689", List.of(new Node<String>("af51e1ce", List.of()),new Node<String>("34fab37c", List.of()),new Node<String>("b9fbb1f7", List.of()))),new Node<String>("3d483ca4", List.of()),new Node<String>("cb4a368b", List.of(new Node<String>("4db7c1e6", List.of()))),new Node<String>("7ee1c6e7", List.of(new Node<String>("c3b3e79a", List.of()))),new Node<String>("ed9b575f", List.of())));
            assertEquals("depth/Test_depth", 3, rr.depth());
        }
        {
            Node<String> rr = new Node<String>("9f74bbf0", List.of(new Node<String>("819634bd", List.of(new Node<String>("111c2b6", List.of())))));
            assertEquals("depth/Test_depth", 3, rr.depth());
        }
        scoring.updateScore("lang:common_list_test_scoring_name",   17.0D);
    }
    @Test
    public void test_leafs() {
        {
            Node<String> rr = new Node<String>("df2a1ee6", List.of());
            assertEquals("leafs/Test_leafs", Set.of( "df2a1ee6"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("a4841ad8", List.of());
            assertEquals("leafs/Test_leafs", Set.of( "a4841ad8"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("2909e765", List.of());
            assertEquals("leafs/Test_leafs", Set.of( "2909e765"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("a7f5aded", List.of());
            assertEquals("leafs/Test_leafs", Set.of( "a7f5aded"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("41804707", List.of());
            assertEquals("leafs/Test_leafs", Set.of( "41804707"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("b7b7ccae", List.of(new Node<String>("8053231d", List.of()),new Node<String>("d5e6171", List.of())));
            assertEquals("leafs/Test_leafs", Set.of( "d5e6171","8053231d"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("bcd046a8", List.of());
            assertEquals("leafs/Test_leafs", Set.of( "bcd046a8"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("74164548", List.of(new Node<String>("1d7303b6", List.of()),new Node<String>("a4086739", List.of())));
            assertEquals("leafs/Test_leafs", Set.of( "1d7303b6","a4086739"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("78b28f7", List.of(new Node<String>("8af1c5bd", List.of())));
            assertEquals("leafs/Test_leafs", Set.of( "8af1c5bd"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("2e4bb1ae", List.of(new Node<String>("c440b7d0", List.of()),new Node<String>("92564df2", List.of()),new Node<String>("580e6451", List.of())));
            assertEquals("leafs/Test_leafs", Set.of( "c440b7d0","92564df2","580e6451"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("a9e0ded6", List.of(new Node<String>("342d84b5", List.of(new Node<String>("d6695572", List.of()),new Node<String>("36165632", List.of()),new Node<String>("523f6700", List.of()))),new Node<String>("33eccc98", List.of(new Node<String>("a7bf1f2c", List.of()))),new Node<String>("bde52981", List.of(new Node<String>("debc0731", List.of()),new Node<String>("bb6bbb2a", List.of()),new Node<String>("22f0e098", List.of()))),new Node<String>("2bbb12d4", List.of(new Node<String>("5f124422", List.of()),new Node<String>("b7e81a3d", List.of()))),new Node<String>("5e33fc3d", List.of(new Node<String>("3391649b", List.of()),new Node<String>("6f2b422e", List.of()),new Node<String>("7fce2c14", List.of())))));
            assertEquals("leafs/Test_leafs", Set.of( "523f6700","5f124422","36165632","bb6bbb2a","3391649b","7fce2c14","d6695572","22f0e098","a7bf1f2c","debc0731","b7e81a3d","6f2b422e"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("6e05c489", List.of(new Node<String>("d894f06", List.of()),new Node<String>("8c01d09d", List.of(new Node<String>("e63a0334", List.of()),new Node<String>("1b7fbe3", List.of())))));
            assertEquals("leafs/Test_leafs", Set.of( "1b7fbe3","d894f06","e63a0334"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("e4b4f959", List.of(new Node<String>("4868d231", List.of())));
            assertEquals("leafs/Test_leafs", Set.of( "4868d231"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("5741b95d", List.of(new Node<String>("10467ee6", List.of(new Node<String>("6a60e138", List.of()))),new Node<String>("1c4cde0a", List.of()),new Node<String>("b6746b3e", List.of()),new Node<String>("9645e354", List.of(new Node<String>("1e38c730", List.of()),new Node<String>("814ff223", List.of()),new Node<String>("cb948a9b", List.of()))),new Node<String>("e059bf25", List.of(new Node<String>("6c8e5af1", List.of()),new Node<String>("a507fc21", List.of())))));
            assertEquals("leafs/Test_leafs", Set.of( "6c8e5af1","6a60e138","1c4cde0a","1e38c730","814ff223","b6746b3e","a507fc21","cb948a9b"), rr.leafs());
        }
        {
            Node<String> rr = new Node<String>("633871d0", List.of());
            assertEquals("leafs/Test_leafs", Set.of( "633871d0"), rr.leafs());
        }
        scoring.updateScore("lang:common_list_test_scoring_name",   17.0D);
    }
    @Test
    public void test_isHeap() {
        {
            Node<String> rr = new Node<String>("333b2fc7", List.of());
            assertEquals("isHeap/Test_isHeap", true, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("8fb9584a", List.of());
            assertEquals("isHeap/Test_isHeap", true, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("1381230", List.of());
            assertEquals("isHeap/Test_isHeap", true, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("6db33215", List.of());
            assertEquals("isHeap/Test_isHeap", true, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("8de94c8e", List.of());
            assertEquals("isHeap/Test_isHeap", true, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("3466432d", List.of(new Node<String>("90b79efc", List.of())));
            assertEquals("isHeap/Test_isHeap", false, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("d85df05e", List.of(new Node<String>("2fc335ba", List.of())));
            assertEquals("isHeap/Test_isHeap", true, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("4753b445", List.of(new Node<String>("abcc131c", List.of())));
            assertEquals("isHeap/Test_isHeap", false, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("c016068c", List.of(new Node<String>("667612f1", List.of())));
            assertEquals("isHeap/Test_isHeap", true, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("30d421d2", List.of(new Node<String>("bbffbbd", List.of())));
            assertEquals("isHeap/Test_isHeap", false, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("162f6a37", List.of(new Node<String>("e2ca504b", List.of(new Node<String>("81756a17", List.of()))),new Node<String>("75a02b94", List.of(new Node<String>("3a8ea733", List.of()),new Node<String>("501bfaf1", List.of()),new Node<String>("cdabe36c", List.of()))),new Node<String>("afd87408", List.of()),new Node<String>("440a0362", List.of())));
            assertEquals("isHeap/Test_isHeap", false, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("3fc035fe", List.of(new Node<String>("a7d81be5", List.of(new Node<String>("2d7e99f7", List.of()),new Node<String>("c7119766", List.of()))),new Node<String>("3c64659c", List.of(new Node<String>("ce03d0aa", List.of()),new Node<String>("189b95f2", List.of()),new Node<String>("9b3c711d", List.of()))),new Node<String>("bca99877", List.of(new Node<String>("c0eb079f", List.of()),new Node<String>("44e1b574", List.of())))));
            assertEquals("isHeap/Test_isHeap", false, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("32f1d12c", List.of());
            assertEquals("isHeap/Test_isHeap", true, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("fa633727", List.of(new Node<String>("11b9c8c9", List.of()),new Node<String>("d394cbfa", List.of(new Node<String>("fa4accd0", List.of()))),new Node<String>("dc124d6f", List.of(new Node<String>("3b5d4ad2", List.of()),new Node<String>("665c8e69", List.of()))),new Node<String>("ebd738e6", List.of(new Node<String>("dc3da62a", List.of()),new Node<String>("8b1f4228", List.of())))));
            assertEquals("isHeap/Test_isHeap", false, rr.isHeap());
        }
        {
            Node<String> rr = new Node<String>("e454758b", List.of(new Node<String>("222306aa", List.of(new Node<String>("bfa8c132", List.of()))),new Node<String>("502c5dc1", List.of()),new Node<String>("c5b75352", List.of(new Node<String>("ccaf2592", List.of()),new Node<String>("d2f06ddf", List.of()))),new Node<String>("93548be5", List.of())));
            assertEquals("isHeap/Test_isHeap", false, rr.isHeap());
        }
        scoring.updateScore("lang:common_list_test_scoring_name",   17.0D);
    }
    @Test
    public void test_atSameLevel() {
        {
            Node<String> rr = new Node<String>("412b5ab3", List.of());
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("412b5ab3", "412b5ab3"));
        }
        {
            Node<String> rr = new Node<String>("e4eb0d7e", List.of());
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("e4eb0d7e", "e4eb0d7e"));
        }
        {
            Node<String> rr = new Node<String>("f00755dd", List.of());
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("f00755dd", "f00755dd"));
        }
        {
            Node<String> rr = new Node<String>("d70571f", List.of());
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("d70571f", "d70571f"));
        }
        {
            Node<String> rr = new Node<String>("12da852a", List.of());
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("12da852a", "12da852a"));
        }
        {
            Node<String> rr = new Node<String>("caaf8ff3", List.of());
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("caaf8ff3", "caaf8ff3"));
        }
        {
            Node<String> rr = new Node<String>("b177480e", List.of(new Node<String>("d44b9270", List.of()),new Node<String>("8d26129c", List.of()),new Node<String>("7858dcc1", List.of())));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("7858dcc1", "7858dcc1"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("7858dcc1", "d44b9270"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("7858dcc1", "8d26129c"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("d44b9270", "7858dcc1"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("d44b9270", "d44b9270"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("d44b9270", "8d26129c"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("8d26129c", "7858dcc1"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("8d26129c", "d44b9270"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("8d26129c", "8d26129c"));
        }
        {
            Node<String> rr = new Node<String>("d68c9f07", List.of());
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("d68c9f07", "d68c9f07"));
        }
        {
            Node<String> rr = new Node<String>("26e2cfba", List.of(new Node<String>("4c319c79", List.of())));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("4c319c79", "4c319c79"));
        }
        {
            Node<String> rr = new Node<String>("f2dd76b8", List.of(new Node<String>("5982fb73", List.of()),new Node<String>("f7669bf0", List.of()),new Node<String>("6d239457", List.of())));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("6d239457", "6d239457"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("6d239457", "5982fb73"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("6d239457", "f7669bf0"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("5982fb73", "6d239457"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("5982fb73", "5982fb73"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("5982fb73", "f7669bf0"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("f7669bf0", "6d239457"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("f7669bf0", "5982fb73"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("f7669bf0", "f7669bf0"));
        }
        {
            Node<String> rr = new Node<String>("1e92de93", List.of());
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("1e92de93", "1e92de93"));
        }
        {
            Node<String> rr = new Node<String>("b15c34bb", List.of(new Node<String>("354276ee", List.of(new Node<String>("11df5929", List.of()))),new Node<String>("994a60dd", List.of()),new Node<String>("59648393", List.of())));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("994a60dd", "994a60dd"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("994a60dd", "11df5929"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("994a60dd", "59648393"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("11df5929", "994a60dd"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("11df5929", "11df5929"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("11df5929", "59648393"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("59648393", "994a60dd"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("59648393", "11df5929"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("59648393", "59648393"));
        }
        {
            Node<String> rr = new Node<String>("b2b7af86", List.of(new Node<String>("8927f9e", List.of()),new Node<String>("9fa63962", List.of())));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("8927f9e", "8927f9e"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("8927f9e", "9fa63962"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("9fa63962", "8927f9e"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("9fa63962", "9fa63962"));
        }
        {
            Node<String> rr = new Node<String>("c39f2bc6", List.of(new Node<String>("1d690db0", List.of()),new Node<String>("16365a8", List.of(new Node<String>("98f52c6d", List.of()))),new Node<String>("da39859b", List.of(new Node<String>("f9f92814", List.of())))));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("1d690db0", "1d690db0"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("1d690db0", "f9f92814"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("1d690db0", "98f52c6d"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("f9f92814", "1d690db0"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("f9f92814", "f9f92814"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("f9f92814", "98f52c6d"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("98f52c6d", "1d690db0"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("98f52c6d", "f9f92814"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("98f52c6d", "98f52c6d"));
        }
        {
            Node<String> rr = new Node<String>("a4481535", List.of(new Node<String>("5c8828f", List.of()),new Node<String>("d41f80d1", List.of(new Node<String>("e411b42e", List.of()))),new Node<String>("c36e21e1", List.of(new Node<String>("6bb40643", List.of()),new Node<String>("4ebad1f1", List.of()),new Node<String>("7a38895b", List.of()))),new Node<String>("82772f0c", List.of())));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("82772f0c", "82772f0c"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("82772f0c", "6bb40643"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("82772f0c", "4ebad1f1"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("82772f0c", "5c8828f"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("82772f0c", "e411b42e"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("82772f0c", "7a38895b"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("6bb40643", "82772f0c"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("6bb40643", "6bb40643"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("6bb40643", "4ebad1f1"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("6bb40643", "5c8828f"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("6bb40643", "e411b42e"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("6bb40643", "7a38895b"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("4ebad1f1", "82772f0c"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("4ebad1f1", "6bb40643"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("4ebad1f1", "4ebad1f1"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("4ebad1f1", "5c8828f"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("4ebad1f1", "e411b42e"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("4ebad1f1", "7a38895b"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("5c8828f", "82772f0c"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("5c8828f", "6bb40643"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("5c8828f", "4ebad1f1"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("5c8828f", "5c8828f"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("5c8828f", "e411b42e"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("5c8828f", "7a38895b"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("e411b42e", "82772f0c"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("e411b42e", "6bb40643"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("e411b42e", "4ebad1f1"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("e411b42e", "5c8828f"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("e411b42e", "e411b42e"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("e411b42e", "7a38895b"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("7a38895b", "82772f0c"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("7a38895b", "6bb40643"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("7a38895b", "4ebad1f1"));
            assertEquals("atSameLevel/Test_atSameLevel", false, rr.atSameLevel("7a38895b", "5c8828f"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("7a38895b", "e411b42e"));
            assertEquals("atSameLevel/Test_atSameLevel", true, rr.atSameLevel("7a38895b", "7a38895b"));
        }
        scoring.updateScore("lang:common_list_test_scoring_name",   33.0D);
    }
}
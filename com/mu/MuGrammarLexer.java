// Generated from /afs/inf.ed.ac.uk/user/s13/s1309096/mu-calculus/MuGrammar.g4 by ANTLR 4.5.1
package com.mu;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MuGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, Var=4, Prop=5, Not=6, And=7, Or=8, Dia=9, Box=10, 
		Mu=11, Nu=12, Dot=13, NL=14, WS=15;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "Var", "Prop", "Not", "And", "Or", "Dia", "Box", 
		"Mu", "Nu", "Dot", "NL", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "' , '", "')'", null, null, "'N'", "'and'", "'or'", "'<a>'", 
		"'[a]'", "'m'", "'n'", "'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "Var", "Prop", "Not", "And", "Or", "Dia", "Box", 
		"Mu", "Nu", "Dot", "NL", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MuGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MuGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\21P\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\6\17F\n"+
		"\17\r\17\16\17G\3\20\6\20K\n\20\r\20\16\20L\3\20\3\20\2\2\21\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21\3\2"+
		"\5\3\2RU\3\2\f\f\4\2\13\f\17\17Q\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\3!\3\2\2\2\5#\3\2\2\2\7\'\3\2\2\2\t)\3\2\2\2\13+\3\2\2\2"+
		"\r-\3\2\2\2\17/\3\2\2\2\21\63\3\2\2\2\23\66\3\2\2\2\25:\3\2\2\2\27>\3"+
		"\2\2\2\31@\3\2\2\2\33B\3\2\2\2\35E\3\2\2\2\37J\3\2\2\2!\"\7*\2\2\"\4\3"+
		"\2\2\2#$\7\"\2\2$%\7.\2\2%&\7\"\2\2&\6\3\2\2\2\'(\7+\2\2(\b\3\2\2\2)*"+
		"\4Z\\\2*\n\3\2\2\2+,\t\2\2\2,\f\3\2\2\2-.\7P\2\2.\16\3\2\2\2/\60\7c\2"+
		"\2\60\61\7p\2\2\61\62\7f\2\2\62\20\3\2\2\2\63\64\7q\2\2\64\65\7t\2\2\65"+
		"\22\3\2\2\2\66\67\7>\2\2\678\7c\2\289\7@\2\29\24\3\2\2\2:;\7]\2\2;<\7"+
		"c\2\2<=\7_\2\2=\26\3\2\2\2>?\7o\2\2?\30\3\2\2\2@A\7p\2\2A\32\3\2\2\2B"+
		"C\7\60\2\2C\34\3\2\2\2DF\t\3\2\2ED\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2"+
		"\2H\36\3\2\2\2IK\t\4\2\2JI\3\2\2\2KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MN\3\2"+
		"\2\2NO\b\20\2\2O \3\2\2\2\5\2GL\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
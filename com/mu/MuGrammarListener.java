// Generated from /afs/inf.ed.ac.uk/user/s13/s1309096/mu-calculus/MuGrammar.g4 by ANTLR 4.5.1
package com.mu;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MuGrammarParser}.
 */
public interface MuGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MuGrammarParser#form}.
	 * @param ctx the parse tree
	 */
	void enterForm(MuGrammarParser.FormContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuGrammarParser#form}.
	 * @param ctx the parse tree
	 */
	void exitForm(MuGrammarParser.FormContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuGrammarParser#nform}.
	 * @param ctx the parse tree
	 */
	void enterNform(MuGrammarParser.NformContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuGrammarParser#nform}.
	 * @param ctx the parse tree
	 */
	void exitNform(MuGrammarParser.NformContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuGrammarParser#and}.
	 * @param ctx the parse tree
	 */
	void enterAnd(MuGrammarParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuGrammarParser#and}.
	 * @param ctx the parse tree
	 */
	void exitAnd(MuGrammarParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuGrammarParser#lpar}.
	 * @param ctx the parse tree
	 */
	void enterLpar(MuGrammarParser.LparContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuGrammarParser#lpar}.
	 * @param ctx the parse tree
	 */
	void exitLpar(MuGrammarParser.LparContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuGrammarParser#comma}.
	 * @param ctx the parse tree
	 */
	void enterComma(MuGrammarParser.CommaContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuGrammarParser#comma}.
	 * @param ctx the parse tree
	 */
	void exitComma(MuGrammarParser.CommaContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuGrammarParser#rpar}.
	 * @param ctx the parse tree
	 */
	void enterRpar(MuGrammarParser.RparContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuGrammarParser#rpar}.
	 * @param ctx the parse tree
	 */
	void exitRpar(MuGrammarParser.RparContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuGrammarParser#or}.
	 * @param ctx the parse tree
	 */
	void enterOr(MuGrammarParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuGrammarParser#or}.
	 * @param ctx the parse tree
	 */
	void exitOr(MuGrammarParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuGrammarParser#diamond}.
	 * @param ctx the parse tree
	 */
	void enterDiamond(MuGrammarParser.DiamondContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuGrammarParser#diamond}.
	 * @param ctx the parse tree
	 */
	void exitDiamond(MuGrammarParser.DiamondContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuGrammarParser#box}.
	 * @param ctx the parse tree
	 */
	void enterBox(MuGrammarParser.BoxContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuGrammarParser#box}.
	 * @param ctx the parse tree
	 */
	void exitBox(MuGrammarParser.BoxContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuGrammarParser#mu}.
	 * @param ctx the parse tree
	 */
	void enterMu(MuGrammarParser.MuContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuGrammarParser#mu}.
	 * @param ctx the parse tree
	 */
	void exitMu(MuGrammarParser.MuContext ctx);
	/**
	 * Enter a parse tree produced by {@link MuGrammarParser#nu}.
	 * @param ctx the parse tree
	 */
	void enterNu(MuGrammarParser.NuContext ctx);
	/**
	 * Exit a parse tree produced by {@link MuGrammarParser#nu}.
	 * @param ctx the parse tree
	 */
	void exitNu(MuGrammarParser.NuContext ctx);
}
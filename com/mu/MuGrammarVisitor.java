// Generated from /afs/inf.ed.ac.uk/user/s13/s1309096/mu-calculus/MuGrammar.g4 by ANTLR 4.5.1
package com.mu;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MuGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MuGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MuGrammarParser#form}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForm(MuGrammarParser.FormContext ctx);
	/**
	 * Visit a parse tree produced by {@link MuGrammarParser#nform}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNform(MuGrammarParser.NformContext ctx);
	/**
	 * Visit a parse tree produced by {@link MuGrammarParser#and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(MuGrammarParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by {@link MuGrammarParser#lpar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLpar(MuGrammarParser.LparContext ctx);
	/**
	 * Visit a parse tree produced by {@link MuGrammarParser#comma}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComma(MuGrammarParser.CommaContext ctx);
	/**
	 * Visit a parse tree produced by {@link MuGrammarParser#rpar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpar(MuGrammarParser.RparContext ctx);
	/**
	 * Visit a parse tree produced by {@link MuGrammarParser#or}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(MuGrammarParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by {@link MuGrammarParser#diamond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiamond(MuGrammarParser.DiamondContext ctx);
	/**
	 * Visit a parse tree produced by {@link MuGrammarParser#box}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBox(MuGrammarParser.BoxContext ctx);
	/**
	 * Visit a parse tree produced by {@link MuGrammarParser#mu}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMu(MuGrammarParser.MuContext ctx);
	/**
	 * Visit a parse tree produced by {@link MuGrammarParser#nu}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNu(MuGrammarParser.NuContext ctx);
}
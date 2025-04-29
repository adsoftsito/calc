package com.adsoft.calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adsoft.calculator.calc.CalculatorLexer;
import com.adsoft.calculator.calc.CalculatorParser;
import com.adsoft.calculator.calc.Listener;
import com.adsoft.calculator.calc.Result;
import com.adsoft.calculator.calc.Statement;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class HelloController {

   @PostMapping("/calc")
   @ResponseBody
   public Result calc(@RequestBody Statement myStatements) {
       
      CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(myStatements.getStatement()));
      CommonTokenStream tokens = new CommonTokenStream(lexer);
 
        CalculatorParser parser = new CalculatorParser(tokens);
        ParseTree tree = parser.expr(); // parse
 
        ParseTreeWalker walker = new ParseTreeWalker();
        Listener listener = new Listener();
        walker.walk(listener, tree);
        System.out.println(listener.getResult());
 
        Result myResult = new Result();
        myResult.setResult(listener.getResult());
 
        return (myResult);
   }
 
  
} 


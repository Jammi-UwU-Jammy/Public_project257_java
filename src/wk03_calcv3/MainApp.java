/*
 * @topic T10170 Desktop Calculator v3
 * @brief main driver program
*/
package wk03_calcv3;

import javax.lang.model.element.VariableElement;

public class MainApp {

    public static void main( String[] args ) {
        
        for(;;) {
            StringBuilder input = new StringBuilder(
                Validator.getString( Validator.sc, "> " )
            );
            if ( "quit".equals( input.toString() ) ) {
                System.out.println( "Bye!" );
                System.exit( 0 );
            }
            Lexer lexer = new Lexer( input, AstNode.variableTable );
            AstNode result = lexer.startRule();
            if ( result.type == AstNode.ERROR ) {
                System.out.println( result.value );
            } else{ /* SUCCEED VERIFYING ERRORS */
                
                //-----------// if user enter just one variable i.e. > x -----------//
                if ( (input.toString().length() <= 2)  ){      
                    if (! result.lookForKey(input.charAt(0))){   //if variable entered wasnt previously written in hashmap
                        System.out.println("Variable you're calculating doesn't exist.");
                    }
                    else {
                        result.nodeEvaluate();                  //if variable exists in hashmap, retrieve its value
                        System.out.println( input.charAt(0)+ " = " + result.getObjValue(input.charAt(0)) );
                    }
                    continue;
                }//one variable
                
                //result.print(0);

                //---------// if user enter an equation-------------//
                else {
                    if ( result.getType() != '=')                   // if user enter types other than '='
                        System.out.println(result.nodeEvaluate());  // then calculate & print on screen
                    else{
                        
                        result.nodeEvaluate();  // if '=' type, then calculate but not print
                    
                    }
                        
                }// type +,-,*,/,%,=
            } /* SUCCEED VERIFYING ERRORS */
           
        }// forever
    }//main
}//class MainApp

/*
 * Copyright (c) 2008, 2011 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package ensemble

import scalafx.Includes._
import ensemble.pages.CategoryPage
import java.io.File
import java.util.HashMap
import java.util.jar.JarFile
import java.net.URI
import ensemble.pages.SamplePage
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayList
import scala.io.Source

import scala.util.control.Exception._

//import ensemble.pages.CategoryPage;
//import ensemble.pages.SamplePage;
//import java.io.BufferedReader;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.StringTokenizer;
//import java.util.jar.JarEntry;
//import java.util.jar.JarFile;
//import javafx.application.ConditionalFeature;
//import javafx.application.Platform;

/**
 * SampleHelper
 *
 */
object SampleHelper {
    final val SAMPLES_PACKAGE_ROOT = "ensemble/samples/";

    def getSamples( rootPage:CategoryPage) {
 
      println(">>getSamples:start " + rootPage)
      allCatch either {
//        try {
            // Get the URL for this class, if it is a jar URL, then get the
            // filename associated with it.
            val classUrl = SampleHelper.getClass.getResource("SampleHelper.class");
            val classUrlString = classUrl.toString()
            // check if its a file or jar, handle each
            if (classUrlString.startsWith("file:")) {
                val classFile = new File(classUrl.toURI())
                val ensemble2Dir = classFile.getParentFile()
                val samplesDir = new File(ensemble2Dir,"samples")
                
//                val samplesDir = new File("""/Users/andy/Projects/ScalaFX/scalafx/demo/ensemble/samples""")
                
      println(">>getSamples:sfindAllSamples-call " + rootPage)
                findAllSamples(samplesDir,rootPage);
      println(">>getSamples:sfindAllSamples-ret " + rootPage.getChildren())
//                findAllSamples(rootPage)
            } else if (classUrlString.startsWith("jar:") && classUrlString.indexOf("!") != -1) {
                // Strip out the "jar:" and everything after and including the "!"
                val jarFileUrl = classUrlString.substring(4, classUrlString.lastIndexOf("!"))
                findAllSamplesInJar(jarFileUrl,rootPage)
            }  else {
                throw new UnsatisfiedLinkError("Invalid URL for class: " + classUrlString);
            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
            
      } match {
        case Right(res) => res
        case Left(e) =>  e.printStackTrace
      }
    }

    def findAllSamples( jarFileUrl:String, dirPage:CategoryPage) {
        // create map for CategoryPages
        val categoryPageMap = new HashMap[String, CategoryPage]();
        // walk jar entries
        val jarFile = new JarFile(new File(new URI(jarFileUrl)))
        val entrysEnum = jarFile.entries()
        while (entrysEnum.hasMoreElements()) {
            val entry = entrysEnum.nextElement()
            val name = entry.getName()
            if (name.startsWith("ensemble/samples/") &&
                    name.endsWith(".java")) {
                val url = "jar:"+jarFileUrl+"!/"+name;
                // create sample page
                val fileName = name.substring(name.lastIndexOf('/')+1,name.length()-5)
                val samplePage = new SamplePage(formatName(fileName),url)
                // add to parent category
                val parentPath = name.substring(SAMPLES_PACKAGE_ROOT.length(),name.lastIndexOf('/'))
                val parentCategoryPage = getCategoryPageForPath(parentPath, dirPage, categoryPageMap)
                parentCategoryPage.getChildren().add(samplePage)
            }
        }
    }

    def findAllSamplesInJar( jarFileUrl:String, dirPage:CategoryPage)  {
        import scala.collection.JavaConversions._
        import scala.collection.JavaConverters._
        val reader = new BufferedReader(new InputStreamReader(SampleHelper.getClass.getResourceAsStream("samplesAll.txt")))
        var line = ""
        val sampleUrls = new ArrayList[String]()
//        try {
            while ((line = reader.readLine()) != null) {
                sampleUrls.add(line);
            }
            reader.close();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
        // create map for CategoryPages
        val categoryPageMap = new HashMap[String, CategoryPage]()
        sampleUrls.foreach { oneSampleUrl =>
//        for (String oneSampleUrl:sampleUrls) {
            val name = oneSampleUrl.substring(oneSampleUrl.indexOf("ensemble/samples/"), oneSampleUrl.length());
            if (name.startsWith("ensemble/samples/") && name.endsWith(".java")) {
                val url = "jar:"+jarFileUrl.replace(" ", "%20")+"!/"+name; 
                // System.out.println("findAllSamplesInJar: url = " + url);
                // create sample page
                val fileName = name.substring(name.lastIndexOf('/')+1,name.length()-5);
                val samplePage = new SamplePage(formatName(fileName),url);
                // add to parent category
                val parentPath = name.substring(SAMPLES_PACKAGE_ROOT.length(),name.lastIndexOf('/'));
                val parentCategoryPage = getCategoryPageForPath(parentPath, dirPage, categoryPageMap);
                parentCategoryPage.getChildren().add(samplePage);
            }
        }
    }

    def  getCategoryPageForPath( path:String, dirPage:CategoryPage, categoryPageMap:java.util.Map[String,CategoryPage]):CategoryPage = {
        var categoryPage = categoryPageMap.get(path)
        if (categoryPage == null) {
            val lastSlash = path.lastIndexOf('/');
            if (lastSlash == -1) {
                // found root level category so create
                categoryPage = new CategoryPage(formatName(path))
                dirPage.getChildren().add(categoryPage);
            } else {
                // get parent
                val parentCategoryPage = getCategoryPageForPath(path.substring(0,lastSlash),dirPage, categoryPageMap);
                // create new sub-category
                categoryPage = new CategoryPage(formatName(path.substring(lastSlash+1,path.length())));
                parentCategoryPage.getChildren().add(categoryPage);
            }
            categoryPageMap.put(path,categoryPage)
        }
        categoryPage
    }

    def findAllSamples( dirPage:CategoryPage) {
        
        val sampleUrls = Source.fromFile("//Users//andy//Projects//ScalaFX//scalafx//demo//samplesAll.txt").getLines.toList
        
//        import scala.collection.JavaConversions._
//        import scala.collection.JavaConverters._
//        //getResourceAsStream for file
//        val reader = new BufferedReader(new InputStreamReader(SampleHelper.getClass.getResourceAsStream("samplesAll.txt")))
//        var line = ""
//        val sampleUrls = new ArrayList[String]()
////        try {
//            while ((line = reader.readLine()) != null) {
//                sampleUrls.add(line);
//            }
//            reader.close();
////        } catch (IOException ioe) {
////            ioe.printStackTrace();
////        }
        val categoryPageMap = new HashMap[String, CategoryPage]()
        sampleUrls.foreach { oneSampleUrl =>
            // create sample page
            val fileName = oneSampleUrl.substring(oneSampleUrl.lastIndexOf('/') + 1, oneSampleUrl.length() - 5);
            val samplePage = new SamplePage(formatName(fileName), oneSampleUrl);
            // add to parent category;
            val pathParts = oneSampleUrl.split("ensemble/samples/");
            //  String parentPath = oneSampleUrl.substring(SAMPLES_PACKAGE_ROOT.length(),oneSampleUrl.lastIndexOf('/'));
            val parentPath = pathParts(1).substring(0, pathParts(1).lastIndexOf('/'));
            val parentCategoryPage = getCategoryPageForPath(parentPath, dirPage, categoryPageMap);
            parentCategoryPage.getChildren().add(samplePage);
        }
    }

    def findAllSamples( dir:File, dirPage:CategoryPage) {
      println(">>findAllSamples - start " + dir + "  " + dirPage )
        dir.listFiles.foreach { child =>
//        for (File child:dir.listFiles()) {
            val name = child.getName()
            if (child.isDirectory()) {
      println(">>findAllSamples - dir " + child.getName)
                val cp = new CategoryPage(formatName(name))
                dirPage.getChildren().add(cp)
                findAllSamples(child, cp)
            } else if(child.getName().endsWith(".scala")) {
                // handle sample src
      println(">>findAllSamples - file " + child.getName)
                val sampleName = name.substring(0,name.lastIndexOf('.'));
                val sp = new SamplePage(formatName(sampleName),child.toURI().toString())
      println(">>findAllSamples - adding " + sp) 
                dirPage.getChildren().add(sp)
      println(">>findAllSamples - added " + dirPage.getChildren())
            }
        }
      println(">>findAllSamples - end" + dirPage.getChildren())
    }

    def formatName( pn: String):String =  {
        // remove ending "Sample" from name
//        (if(packageName.endsWith("Sample"))   packageName.substring(0,packageName.length()-"Sample".length()) else packageName)
//         	.replaceAll("([\\p{Upper}\\d])"," $1")
//        	.substring(0,1).toUpperCase() + packageName.substring(1)
//        	.trim()
      
        var packageName = pn
        if(packageName.endsWith("Sample")) packageName = packageName.substring(0,packageName.length()-"Sample".length());
        // add spaces
        packageName = packageName.replaceAll("([\\p{Upper}\\d])"," $1");
        // uppercase first char
        packageName = packageName.substring(0,1).toUpperCase() + packageName.substring(1);
        return packageName.trim();
    }
}

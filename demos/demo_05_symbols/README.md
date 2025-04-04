# Symbols

## Use case

```bash
We work in a small startup and we want to develop a small site quickly.
```

## Prompt 1

```bash
Using the screenshots from the images: snapshot_1.png, snapshot_2.png, snapshot_3.png 
create the web document index.html using the ideas from the 3 screenshots and put the html content secuentially with a style of HTML5. 
When analyze any snapshot identify the images and replace the images with placeholders from:
https://placehold.co/
put the styles in the folder css. 
Put the development in the folder ./demos/demo_05_symbols/site

If you have doubts in the implementation use the original site: https://as.com/
```

## Prompt 2

- https://pikwy.com/

```bash
Using the screenshot from the images: snapshot.png
create the web document index.html using the ideas from the 3 screenshots and put the html content secuentially with a style of HTML5. 
When analyze any snapshot identify the images and replace the images with placeholders from:
https://placehold.co/
put the styles in the folder css. 
Put the development in the folder ./demos/demo_05_symbols/site

If you have doubts in the implementation use the original site: https://as.com/
```

## Prompt 3

- https://docs.google.com/forms/d/13GTAFsBqqcZF_oTRtYTyLaunjTuawzBmCFciLFbsJpo/viewform?edit_requested=true
- https://pikwy.com/

```bash
Using the screenshot from the images: snapshot.png
create the web document index.html using the ideas from the 3 screenshots and put the html content secuentially with a style of HTML5. 
When analyze any snapshot identify the images and replace the images with placeholders from:
https://placehold.co/
put the styles in the folder css. 
Put the development in the folder ./demos/demo_05_symbols/site

If you have doubts in the implementation use the original site: https://docs.google.com/forms/d/13GTAFsBqqcZF_oTRtYTyLaunjTuawzBmCFciLFbsJpo/viewform?edit_requested=true
```

## Verify

```bash
jwebserver -p 8001 -d "$(pwd)/demos/demo_05_symbols/site"
open http://localhost:8001/
```
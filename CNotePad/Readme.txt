This project is an improvement compared to BNotePad 
The main changes are:

- BNote* has been renamed to CNote*
- URI-constants moved from CNotePad to CNotePadProvider
- no support for COPY/PASTE
- no support for Clipboard
- no support for storing items in asset-files

- CNotesList inherits now from Activity, not from ListActivity. 
  CNotesList contains now a ListView and a Button. The Layout is defined 
  in noteslist.xml (note that the action of the Button is bound within 
  CNotesList.java)

- CNoteEditor shows now both title and content. The content can be edited
  immediately, to edit the title the user has to press the Edit-button first.
  
- CNoteEditor shows now lastModified-Attribute for item  

Bugs:
- layout could be nicer


- source comments have largely remained unchained
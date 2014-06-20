// You can find instructions for this file here:
// http://www.treeview.net

// Decide if the names are links or just the icons
USETEXTLINKS = 1  //replace 0 with 1 for hyperlinks

// Decide if the tree is to start all open or just showing the root folders
STARTALLOPEN = 0 //replace 0 with 1 to show the whole tree

ICONPATH = 'Support/' //change if the gif's folder is a subfolder, for example: 'images/'


USEICONS = 1

{
foldersTree = gFld("Title", "title.htm")
foldersTree.iconSrc = ICONPATH + "Gif/globe.gif"
Diag_Node = insFld(foldersTree, gFld("PULSEIRAS.DM1", "javascript:parent.op()"))
Diag_Node.iconSrc = ICONPATH + "Gif/ERSTUDIO.gif"
Diag_Node.iconSrcClosed = ICONPATH + "Gif/ERSTUDIO.gif"
Model_Node = insFld(Diag_Node, gFld("Logical", "javascript:parent.op()"))
Model_Node.iconSrc = ICONPATH + "Gif/logical.gif"
Model_Node.iconSrcClosed = ICONPATH + "Gif/logical.gif"
{
Submodel1 = insFld(Model_Node, gFld("Main Model", "javascript:parent.op()"))
Submodel1.iconSrc = ICONPATH + "Gif/logmain.gif"
Submodel1.iconSrcClosed = ICONPATH + "Gif/logmain.gif"
SubmodelImgNode = insDoc(Submodel1, gLnk("R", "Model Image", "Model1/Submodel1_img.htm"))
SubmodelImgNode.iconSrc = ICONPATH + "Gif/image.gif"
SubmodelImgNode.altTxt = "Model Image"
}
{
Submodel18 = insFld(Model_Node, gFld("Ura-Asterisk", "javascript:parent.op()"))
Submodel18.iconSrc = ICONPATH + "Gif/submodel.gif"
Submodel18.iconSrcClosed = ICONPATH + "Gif/submodel.gif"
SubmodelImgNode = insDoc(Submodel18, gLnk("R", "Model Image", "Model1/Submodel18_img.htm"))
SubmodelImgNode.iconSrc = ICONPATH + "Gif/image.gif"
SubmodelImgNode.altTxt = "Model Image"
}
Model_Node = insFld(Diag_Node, gFld("GAC", "javascript:parent.op()"))
Model_Node.iconSrc = ICONPATH + "Gif/physical.gif"
Model_Node.iconSrcClosed = ICONPATH + "Gif/physical.gif"
{
Submodel17 = insFld(Model_Node, gFld("Main Model", "javascript:parent.op()"))
Submodel17.iconSrc = ICONPATH + "Gif/grnfldr.gif"
Submodel17.iconSrcClosed = ICONPATH + "Gif/grnfldr.gif"
SubmodelImgNode = insDoc(Submodel17, gLnk("R", "Model Image", "Model17/Submodel17_img.htm"))
SubmodelImgNode.iconSrc = ICONPATH + "Gif/image.gif"
SubmodelImgNode.altTxt = "Model Image"
}
}

package com.renee.investment.database;

import parseRelate.ParseConstant;
import addItem.Product1Object;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter
{
public static final String DB_ACTION = "db_action" ;//LogCat

private static final String DB_NAME = "people.db" ;//数据库名
private static final String DB_TABLE = "peopleinfo" ;//数据库表名
private static final int DB_VERSION = 1 ;//数据库版本号


private SQLiteDatabase db ;
private Context xContext ;
private DBOpenHelper dbOpenHelper ;
public DBAdapter(Context context)
{
xContext = context ;
}

/** 空间不够存储的时候设为只读
* @throws SQLiteException
*/
public void open() throws SQLiteException
{
dbOpenHelper = new DBOpenHelper(xContext, DB_NAME, null,DB_VERSION);
try
{
db = dbOpenHelper.getWritableDatabase();
}
catch (SQLiteException e)
{
db = dbOpenHelper.getReadableDatabase();
}
}

/**
* 调用SQLiteDatabase对象的close()方法关闭数据库
*/
public void close()
{
if(db != null)
{
db.close();
db = null;
}
}
/**
* 向表中添加一条数据
* @param people
* @return
*/
public long insert(Product1Object object)
{
ContentValues newProduct = new ContentValues();

newProduct.put(ParseConstant.KEY_BANKNAME, object.bankName);
newProduct.put(ParseConstant.KEY_AMOUNT, object.moneyAmount);
newProduct.put(ParseConstant.KEY_YIELD, object.annualYield);
newProduct.put(ParseConstant.KEY_INTEREST, object.interest);

/*newValues.put(KEY_NAME, people.Name);
newValues.put(KEY_AGE, people.Age);
newValues.put(KEY_HEIGHT, people.Height);*/

return db.insert(DB_TABLE, null, newProduct);
}


/**
* 根据输入ID删除一条数据
* @param id
* @return
*/
public long deleteOneData(long id)
{
return db.delete(DB_TABLE, ParseConstant.KEY_ID+"="+id, null );
}
/**
* 删除所有数据
* @return
*/
public long deleteAllData()
{
return db.delete(DB_TABLE, null, null);
}
/**
* 根据id查询数据的代码
* @param id
* @return
*/
public Product1Object[] queryOneData(long id)
{
Cursor result = db.query(DB_TABLE, new String[] {ParseConstant.KEY_ID,ParseConstant.KEY_BANKNAME,
		ParseConstant.KEY_AMOUNT,ParseConstant.KEY_YIELD,ParseConstant.KEY_INTEREST}, 
		ParseConstant.KEY_ID+"="+id, null, null, null, null);
return ConvertToProduct1Object(result) ;
}
/**
 * 查询全部数据
 * @return
 */
public Product1Object[] queryAllData()
{
    Cursor result = db.query(DB_TABLE, new String[] {ParseConstant.KEY_ID,ParseConstant.KEY_BANKNAME,
    		ParseConstant.KEY_AMOUNT,ParseConstant.KEY_YIELD,ParseConstant.KEY_INTEREST}, 
            null, null, null, null, null);
    return ConvertToProduct1Object(result);
}

public long updateOneData(long id ,Product1Object object)
{
    ContentValues newProduct = new ContentValues();

newProduct.put(ParseConstant.KEY_BANKNAME, object.bankName);
newProduct.put(ParseConstant.KEY_AMOUNT, object.moneyAmount);
newProduct.put(ParseConstant.KEY_YIELD, object.annualYield);
newProduct.put(ParseConstant.KEY_INTEREST, object.interest);
    
    return db.update(DB_TABLE, newProduct, ParseConstant.KEY_ID+"="+id, null);
}

private Product1Object[] ConvertToProduct1Object(Cursor cursor)
{
    int resultCounts = cursor.getCount();
    if(resultCounts == 0 || !cursor.moveToFirst())
    {
        return null ;
    }
    Product1Object[] objects = new Product1Object[resultCounts];
    Log.i(DB_ACTION, "object len:"+objects.length);
    for (int i = 0; i < resultCounts; i++)
    {
    	objects[i] = new Product1Object();
    	objects[i].ID = cursor.getInt(0);
    	objects[i].bankName = cursor.getString(cursor.getColumnIndex(ParseConstant.KEY_BANKNAME));
    	objects[i].moneyAmount  = cursor.getInt(cursor.getColumnIndex(ParseConstant.KEY_AMOUNT));
    	objects[i].annualYield = cursor.getFloat(cursor.getColumnIndex(ParseConstant.KEY_YIELD));
    	objects[i].interest = cursor.getFloat(cursor.getColumnIndex(ParseConstant.KEY_INTEREST));

        Log.i(DB_ACTION, "object "+i+"info :"+objects[i].toString());
        cursor.moveToNext();
    }
    return objects;
}



private static class DBOpenHelper extends SQLiteOpenHelper
{

    private static final String DB_CREATE=
    "CREATE TABLE "+DB_TABLE
    +" ("+ParseConstant.KEY_ID+" integer primary key autoincrement, "
    +ParseConstant.KEY_BANKNAME+" text not null, "
    +ParseConstant.KEY_AMOUNT+" float,"+ParseConstant.KEY_INTEREST+" float,"+
    ParseConstant.KEY_YIELD+" float);";
    public DBOpenHelper(Context context, String name,
            CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DB_CREATE);
        Log.i(DB_ACTION, "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion)
    {

        _db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(_db);
        Log.i(DB_ACTION, "Upgrade");
    }

}
}

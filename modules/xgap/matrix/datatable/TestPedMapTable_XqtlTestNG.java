package matrix.datatable;

import java.io.File;
import java.util.Iterator;

import org.molgenis.framework.tupletable.TableException;
import org.molgenis.framework.tupletable.TupleTable;
import org.molgenis.util.tuple.Tuple;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestPedMapTable_XqtlTestNG
{
	TupleTable table;

	@BeforeMethod
	public void setup() throws Exception
	{
		table = new PedMapTupleTable(new File(TestPedMapTable_XqtlTestNG.class.getResource("test.ped").getFile()),
				new File(TestPedMapTable_XqtlTestNG.class.getResource("test.map").getFile()));
	}

	@Test
	public void testGetCount() throws TableException
	{
		Assert.assertEquals(table.getCount(), 6);
	}

	@Test
	public void testGetColCount() throws TableException
	{
		Assert.assertEquals(table.getColCount(), 8);
	}

	@Test
	public void testWithoutLimit()
	{
		table.setFirstColumnFixed(true);
		Iterator<Tuple> iter = table.iterator();
		int count = 0;
		while (iter.hasNext())
		{
			count++;
			Tuple tuple = iter.next();

			Assert.assertEquals(tuple.getNrCols(), 8);
			Assert.assertEquals(tuple.getInt("FamilyID").intValue(), count);

			if (count == 1)
			{
				Assert.assertEquals(tuple.getString("snp2"), "G T");
			}
			else if (count == 2)
			{
				Assert.assertEquals(tuple.getString("snp2"), "T G");
			}
			else if (count == 3)
			{
				Assert.assertEquals(tuple.getString("snp2"), "G G");
			}
			else if (count == 4)
			{
				Assert.assertEquals(tuple.getString("snp2"), "T T");
			}
			else if (count == 2)
			{
				Assert.assertEquals(tuple.getString("snp2"), "G T");
			}
			else if (count == 2)
			{
				Assert.assertEquals(tuple.getString("snp2"), "T T");
			}
		}

		Assert.assertEquals(count, 6);
	}

	@Test
	public void testRowLimitOffset()
	{
		table.setOffset(2);
		Iterator<Tuple> iter = table.iterator();
		int count = 0;
		while (iter.hasNext())
		{
			count++;
			Tuple tuple = iter.next();
			if (count == 1)
			{
				Assert.assertEquals(tuple.getString("FamilyID"), "3");
			}
		}
		Assert.assertEquals(count, 4);
	}

	@Test
	public void testColLimit()
	{
		table.setColLimit(2);
		Iterator<Tuple> iter = table.iterator();
		Tuple tuple = iter.next();
		Assert.assertEquals(tuple.getNrCols(), 2);
	}

	@Test
	public void testColOffset()
	{
		table.setColOffset(2);
		Iterator<Tuple> iter = table.iterator();
		Tuple tuple = iter.next();
		Assert.assertEquals(tuple.getNrCols(), 6);
		Assert.assertEquals(tuple.getColNames().iterator().next(), "FatherID");
	}

	@Test
	public void testColLimitOffset()
	{
		table.setColOffset(1);
		table.setColLimit(4);
		Iterator<Tuple> iter = table.iterator();
		Tuple tuple = iter.next();
		Assert.assertEquals(tuple.getNrCols(), 4);
		Assert.assertEquals(tuple.getColNames().iterator().next(), "FamilyID");
	}

}